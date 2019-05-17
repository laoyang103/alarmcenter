package cn.junety.alarm.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import cn.junety.alarm.api.impl.Level;
import cn.junety.alarm.server.vo.AlarmForm;
import cn.junety.alarm.server.vo.SyslogBean;
import cn.junety.alarm.server.syslog.SyslogParser;

/**
 * Created by caijt on 2017/1/28.
 */
@Service
public class SyslogService {

  private static final Logger logger = LoggerFactory.getLogger(SyslogService.class);

  private SyslogParser syslogParser = new SyslogParser();

  // private String ipaddr;
  // private int port;
  // private String facility;
  // private String severity;
  // private int timestamp;
  // private String hostname;
  // private String Message;

  //   private Integer code;
  //   private String routeKey;
  //   private boolean isTest;

  private AlarmForm syslogToAlarmForm(SyslogBean sysbean) {
    AlarmForm af = new AlarmForm();
    af.setIp(sysbean.getIpaddr());
    af.setContent(sysbean.getMessage());
    return af;
  }

  public void addSyslogByString(String ipstr, int port, String sysstr) {
    SyslogBean sysbean = new SyslogBean();
    try {
      sysbean = syslogParser.parseMessage(sysstr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    sysbean.setIpaddr(ipstr);
    sysbean.setPort(port);
    System.out.println("recv from [" + ipstr + ":" + port + "] " + sysstr);
  }
}
