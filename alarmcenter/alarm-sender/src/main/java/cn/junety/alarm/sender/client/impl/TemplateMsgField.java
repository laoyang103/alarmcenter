package cn.junety.alarm.sender.client.impl;

public class TemplateMsgField {

  private String value;
  private String color;

  public TemplateMsgField(String value, String color) {
    this.value = value;
    this.color = color;
  }

  public void setValue(String value) {
    this.value = value;
  }
  public String getValue() {
    return value;
  }

  public void setColor(String color) {
    this.color = color;
  }
  public String getColor() {
    return color;
  }
}
