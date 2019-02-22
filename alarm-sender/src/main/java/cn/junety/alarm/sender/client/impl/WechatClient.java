package cn.junety.alarm.sender.client.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.io.IOException;

import cn.junety.alarm.base.common.ConfigKey;
import cn.junety.alarm.base.entity.QueueMessage;
import cn.junety.alarm.base.entity.AccessToken;
import cn.junety.alarm.base.util.HttpUtil;
import cn.junety.alarm.base.util.WechatUtil;
import cn.junety.alarm.sender.client.Client;
import cn.junety.alarm.sender.entity.TemplateMsg;
import cn.junety.alarm.sender.entity.TemplateMsgField;
import com.alibaba.fastjson.JSON;

/**
 * Created by caijt on 2017/3/29.
 */
public class WechatClient extends Client {

  public WechatClient(String name, String queueName) {
    super(name, queueName, "wechat");
  }

  private void sendTemplate(String toUser, String content) {
    int i;
    Map<String, Object> dataMap = new HashMap<String, Object>();
    String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    TemplateMsg tm = new TemplateMsg();
    String[] fieldSplit = content.split("\n");
    for (i = 0; i < WechatUtil.WX_TEMPLATE_KEY.length; i++) {
      TemplateMsgField tmf = new TemplateMsgField(fieldSplit[i], "#173177");
      dataMap.put(WechatUtil.WX_TEMPLATE_KEY[i], tmf);
    }

    tm.setTouser(toUser);
    tm.setTemplate_id(WechatUtil.WX_TEMPLATE_ID);
    tm.setUrl("http://61.48.51.134/login");
    tm.setData(dataMap);

    String postData = JSON.toJSONString(tm);
    logger.info("send wechat template msg {}", postData);

    AccessToken accessToken = WechatUtil.getAccessTokenFromFile();
    try {
      HttpUtil.doPost(sendUrl.replace("ACCESS_TOKEN", accessToken.getAccess_token()), postData);
    } catch (IOException e) {
      logger.error("post wechat template failed {}", e);
    }
  }

  @Override
  protected int send(String message) {
    try {
      QueueMessage queueMessage = JSON.parseObject(message, QueueMessage.class);
      List<String> toUserList = queueMessage.getReceivers();

      for (String toUser: toUserList) {
        sendTemplate(toUser, queueMessage.getContent());
      }
      this.markDeliveryStatus(queueMessage.getLogId(), channel);
      return queueMessage.getReceivers().size();
    } catch (Exception e) {
      logger.error("handle mail message error, caused by", e);
      return 0;
    }
  }

  @Override
  protected String getPushQuantityKey() {
    return ConfigKey.WECHAT_PUSH_QUANTITY.value();
  }

  @Override
  protected String getPushDailyKey() {
    return ConfigKey.WECHAT_PUSH_DAILY.value();
  }
}
