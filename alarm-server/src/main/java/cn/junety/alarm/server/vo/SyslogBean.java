package cn.junety.alarm.server.vo;

public class SyslogBean {

  private String ipaddr;
  private int port;
  private String facility;
  private String severity;
  private int timestamp;
  private String hostname;
  private String Message;

  public void setIpaddr(String ipaddr) {
    this.ipaddr = ipaddr;
  }
  public String getIpaddr() {
    return ipaddr;
  }

  public void setPort(int port) {
    this.port = port;
  }
  public int getPort() {
    return port;
  }

  public void setFacility(String facility) {
    this.facility = facility;
  }
  public String getFacility() {
    return facility;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }
  public String getSeverity() {
    return severity;
  }

  public void setTimestamp(int timestamp) {
    this.timestamp = timestamp;
  }
  public int getTimestamp() {
    return timestamp;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }
  public String getHostname() {
    return hostname;
  }

  public void setMessage(String Message) {
    this.Message = Message;
  }
  public String getMessage() {
    return Message;
  }
}
