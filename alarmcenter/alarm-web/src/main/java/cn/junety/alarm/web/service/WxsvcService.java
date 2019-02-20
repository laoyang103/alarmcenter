package cn.junety.alarm.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.junety.alarm.base.entity.ReplyTextMsg;
import cn.junety.alarm.base.entity.User;
import cn.junety.alarm.web.dao.UserDao;

import java.util.Map;

@Service
public class WxsvcService {

  @Autowired
  private UserDao userDao;

  private static String WECHART_MSGTYPE_TEXT = "text";
  private static String WECHART_MSGTYPE_EVENT = "event";

  private Logger logger = LoggerFactory.getLogger(WxsvcService.class);

  private String processText(Map<String, String> msgMap) {
    ReplyTextMsg rtm = new ReplyTextMsg();
    rtm.setCreateTime();
    rtm.setMsgType(WECHART_MSGTYPE_TEXT);
    rtm.setFromUserName(msgMap.get("ToUserName"));
    rtm.setToUserName(msgMap.get("FromUserName"));
    rtm.setContent("你刚才说的是：" + msgMap.get("Content"));
    return rtm.msg2Xml();
  }

  private String processEvent(Map<String, String> msgMap) {
    String msg = "功能开发中，敬请关注。";
    String event = msgMap.get("Event");
    String fromUserName = msgMap.get("FromUserName");
    String toUserName = msgMap.get("ToUserName");

    if (event.equals("CLICK")) {
      String btn = msgMap.get("EventKey");
      if (btn.equals("GET_OPENID")) {
        msg = "您的ID是：" + fromUserName;
      }
    } else if (event.equals("subscribe")) {
      msg = "您好，欢迎关注北京协软科技有限公司，您的ID是：" + fromUserName;
    }
    ReplyTextMsg rtm = new ReplyTextMsg();
    rtm.setCreateTime();
    rtm.setMsgType(WECHART_MSGTYPE_TEXT);
    rtm.setFromUserName(toUserName);
    rtm.setToUserName(fromUserName);
    rtm.setContent(msg);

    return rtm.msg2Xml();
  }

  public String processMessage(Map<String, String> msgMap) {
    String msgType = msgMap.get("MsgType");

    if (msgType.equals(WECHART_MSGTYPE_TEXT)) {
      return processText(msgMap);
    } else if (msgType.equals(WECHART_MSGTYPE_EVENT)) {
      return processEvent(msgMap);
    } else {
      return "";
    }
  }

  public void bindWxOpenid(User user, String wxopenid) {
    user.setWechat(wxopenid);
    userDao.update(user);
  }
}
