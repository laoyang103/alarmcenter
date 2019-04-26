package cn.junety.alarm.server.syslog;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.junety.alarm.server.vo.SyslogBean;

/**
 * Parses a syslog message with RFC 3164 or RFC 5424 date format
 */
public class SyslogParser {

  private final static Pattern TWO_SPACES = Pattern.compile("  ");

  private final static DateTimeFormatter rfc3164Format = DateTimeFormat.forPattern("MMM d HH:mm:ss").withZoneUTC();

  private final static int RFC3164_LEN = 15;

  private final static int RFC5424_PREFIX_LEN = 19;

  private final DateTimeFormatter timeParser;

  private Cache<String, Long> timestampCache;

  private static HashMap<Integer, String> facilityMap = new HashMap<Integer, String>();
  private static HashMap<Integer, String> securityMap = new HashMap<Integer, String>();

  public SyslogParser() {
    timeParser = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss").withZoneUTC();
    timestampCache = CacheBuilder.newBuilder().maximumSize(1000).build(
        new CacheLoader<String, Long>() {

        @Override
        public Long load(String key) throws Exception {
        return timeParser.parseMillis(key);
        }
        });
    initFacilityMap();
    intiSecurityMap();
  }

  public SyslogBean parseMessage(String msg) throws IOException {
    int pos = 0;
    int msgLen = msg.length();

    SyslogBean sysbean = new SyslogBean();

    if (msg.charAt(pos) != '<') {
      return null;
    }
    int end = msg.indexOf('>');
    if (end < 0 || end > 6) {
      return null;
    }

    int pri = Integer.parseInt(msg.substring(1, end));
    sysbean.setFacility(facilityMap.get(pri / 8));
    sysbean.setSeverity(securityMap.get(pri % 8));

    if (msgLen <= end + 1) {
      throw new IllegalArgumentException("bad format: no data except priority " + msg);
    }
    pos = end + 1;
    if (msgLen > pos + 2 && "1 ".equals(msg.substring(pos, pos + 2))) {
      pos += 2;
    }

    long timestamp;
    char ch = msg.charAt(pos);
    if (ch == '-') {
      timestamp = System.currentTimeMillis();
      if (msgLen <= pos + 2) {
        throw new IllegalArgumentException("bad syslog format (missing hostname)");
      }
      pos += 2;
    } else if (ch >= 'A' && ch <= 'Z') {
      if (msgLen <= pos + RFC3164_LEN) {
        throw new IllegalArgumentException("bad timestamp format");
      }
      timestamp = parseRFC3164Time(msg.substring(pos, pos + RFC3164_LEN));
      pos += RFC3164_LEN + 1;
    } else {
      int sp = msg.indexOf(' ', pos);
      if (sp == -1) {
        throw new IllegalArgumentException("bad timestamp format");
      }
      timestamp = parseRFC5424Date(msg.substring(pos, sp));
      pos = sp + 1;
    }
    sysbean.setTimestamp((int )timestamp/1000);

    int ns = msg.indexOf(' ', pos);
    if (ns == -1) {
      throw new IllegalArgumentException("bad syslog format (missing hostname)");
    }
    String hostname = msg.substring(pos, ns);
    sysbean.setHostname(hostname);

    String data;
    if (msgLen > ns + 1) {
      pos = ns + 1;
      data = msg.substring(pos);
    } else {
      data = msg;
    }
    sysbean.setMessage(data);
    return sysbean;
  }

  private Long parseRFC5424Date(String msg) {
    int len = msg.length();
    if (len <= RFC5424_PREFIX_LEN) {
      throw new IllegalArgumentException("bad format: not a valid RFC5424 timestamp: " + msg);
    }
    String timestampPrefix = msg.substring(0, RFC5424_PREFIX_LEN);
    Long timestamp = timestampCache.getIfPresent(timestampPrefix);
    int pos = RFC5424_PREFIX_LEN;
    if (timestamp == null) {
      throw new IllegalArgumentException("parse error: timestamp is null");
    }
    if (msg.charAt(pos) == '.') {
      boolean found = false;
      int end = pos + 1;
      if (len <= end) {
        throw new IllegalArgumentException("bad timestamp format (no TZ)");
      }
      while (!found) {
        char ch = msg.charAt(end);
        if (ch >= '0' && ch <= '9') {
          end++;
        } else {
          found = true;
        }
      }
      if (end - (pos + 1) > 0) {
        long milliseconds = (long) (Double.parseDouble(msg.substring(pos, end)) * 1000.0);
        timestamp += milliseconds;
      } else {
        throw new IllegalArgumentException("bad format: invalid timestamp (fractional portion): " + msg);
      }
      pos = end;
    }
    char ch = msg.charAt(pos);
    if (ch != 'Z') {
      if (ch == '+' || ch == '-') {
        if (len <= pos + 5) {
          throw new IllegalArgumentException("bad format: invalid timezone: " + msg);
        }
        int sign = ch == '+' ? +1 : -1;
        char[] hourzone = new char[5];
        for (int i = 0; i < 5; i++) {
          hourzone[i] = msg.charAt(pos + 1 + i);
        }
        if (hourzone[0] >= '0' && hourzone[0] <= '9'
            && hourzone[1] >= '0' && hourzone[1] <= '9'
            && hourzone[2] == ':'
            && hourzone[3] >= '0' && hourzone[3] <= '9'
            && hourzone[4] >= '0' && hourzone[4] <= '9') {
          int hourOffset = Integer.parseInt(msg.substring(pos + 1, pos + 3));
          int minOffset = Integer.parseInt(msg.substring(pos + 4, pos + 6));
          timestamp -= sign * ((hourOffset * 60) + minOffset) * 60000;
        } else {
          throw new IllegalArgumentException("bad format: invalid timezone: " + msg);
        }
      }
    }
    return timestamp;
  }

  private Long parseRFC3164Time(String timestamp) {
    DateTime now = DateTime.now();
    int year = now.getYear();
    timestamp = TWO_SPACES.matcher(timestamp).replaceFirst(" ");
    DateTime date;
    try {
      date = rfc3164Format.parseDateTime(timestamp);
    } catch (Exception e) {
      return 0L;
    }
    DateTime fixed = date.withYear(year);
    if (fixed.isAfter(now) && fixed.minusMonths(1).isAfter(now)) {
      fixed = date.withYear(year - 1);
    } else if (fixed.isBefore(now) && fixed.plusMonths(1).isBefore(now)) {
      fixed = date.withYear(year + 1);
    }
    return fixed.getMillis();
  }

  private void initFacilityMap() {
    facilityMap.put(0,  "kernel messages");
    facilityMap.put(1,  "user-level messages");
    facilityMap.put(2,  "mail system");
    facilityMap.put(3,  "system daemons");
    facilityMap.put(4,  "security/authorization messages (note 1)");
    facilityMap.put(5,  "messages generated internally by syslogd");
    facilityMap.put(6,  "line printer subsystem");
    facilityMap.put(7,  "network news subsystem");
    facilityMap.put(8,  "UUCP subsystem");
    facilityMap.put(9,  "clock daemon (note 2)");
    facilityMap.put(10, "security/authorization messages (note 1)");
    facilityMap.put(11, "FTP daemon");
    facilityMap.put(12, "NTP subsystem");
    facilityMap.put(13, "log audit (note 1)");
    facilityMap.put(14, "log alert (note 1)");
    facilityMap.put(15, "clock daemon (note 2)");
    facilityMap.put(16, "local use 0  (local0)");
    facilityMap.put(17, "local use 1  (local1)");
    facilityMap.put(18, "local use 2  (local2)");
    facilityMap.put(19, "local use 3  (local3)");
    facilityMap.put(20, "local use 4  (local4)");
    facilityMap.put(21, "local use 5  (local5)");
    facilityMap.put(22, "local use 6  (local6)");
  }

  private void intiSecurityMap() {
    securityMap.put(0, "Emergency");
    securityMap.put(1, "Alert");
    securityMap.put(2, "Critical");
    securityMap.put(3, "Error");
    securityMap.put(4, "Warning");
    securityMap.put(5, "Notice");
    securityMap.put(6, "Informational");
    securityMap.put(7, "Debug");
  }
}
