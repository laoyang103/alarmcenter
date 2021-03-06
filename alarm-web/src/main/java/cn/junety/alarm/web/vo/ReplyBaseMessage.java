package cn.junety.alarm.web.vo;

import com.thoughtworks.xstream.XStream;

public class ReplyBaseMessage {

  protected String ToUserName;
  protected String FromUserName;
  protected String CreateTime;
  protected String MsgType;

  public void setToUserName(String toUserName) {
    ToUserName = toUserName;
  }

  public void setFromUserName(String fromUserName) {
    FromUserName = fromUserName;
  }

  /**
   * 返回时间戳给微信服务器
   */
  public void setCreateTime() {
    CreateTime=String.valueOf(System.currentTimeMillis()).substring(0,10);

  }

  public void setMsgType(String msgType) {
    MsgType = msgType;
  }

  public String getToUserName() {
    return ToUserName;
  }

  public String getFromUserName() {
    return FromUserName;
  }

  public String getCreateTime() {
    return CreateTime;
  }

  public String getMsgType() {
    return MsgType;
  }

  public String msg2Xml(){
    XStream xstream=new XStream();
    xstream.alias("xml", this.getClass());
    return xstream.toXML(this);
  }
}
