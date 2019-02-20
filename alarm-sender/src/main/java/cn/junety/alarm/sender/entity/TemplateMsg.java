package cn.junety.alarm.sender.entity;

import java.util.Map;

public class TemplateMsg {

  private String touser;
  private String template_id;
  private String url;
  private Map<String, Object> data;

  public void setTouser(String touser) {
    this.touser = touser;
  }
  public String getTouser() {
    return touser;
  }

  public void setTemplate_id(String template_id) {
    this.template_id = template_id;
  }
  public String getTemplate_id() {
    return template_id;
  }

  public void setUrl(String url) {
    this.url = url;
  }
  public String getUrl() {
    return url;
  }


  public void setData(Map<String, Object> data) {
    this.data = data;
  }
  public Map<String, Object> getData() {
    return data;
  }

}
