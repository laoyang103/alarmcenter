package cn.junety.alarm.web.vo;

import com.thoughtworks.xstream.XStream;

public class ReplyTextMsg extends ReplyBaseMessage {

  private String Content;

  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    Content = content;
  }
}
