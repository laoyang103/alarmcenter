package cn.junety.alarm.sender.client.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import cn.junety.alarm.base.common.ConfigKey;
import cn.junety.alarm.base.entity.QueueMessage;
import cn.junety.alarm.sender.client.Client;
import com.alibaba.fastjson.JSON;

/**
 * Created by caijt on 2017/3/29.
 */
public class WechatClient extends Client {

  private String[] WX_TEMPLATE_KEY = new String[]{"first", "remark", "keyword1", "keyword4", "keyword2", "keyword5", "keyword3"};
  private String WX_TEMPLATE_ID = "LsEJZoMCrli5-YV7JN4cmQbNRMtOFe4FYmkgNU9EIhI";
  private String WX_ACCESS_TOKEN = "18_5p9y2T1H7l00D7rQ359WjOQd2HJukvOFASuoHFs2brG-anVSGJ2OqOaY_Mu_ApPMsEcnqlVfyaczqQ0FHkhWq-248mHLhk_nyIGaYcpLwM8lrh3yWFzhmUP4RN1K3sSgvJDu4C3tDSzurI1mVWUdACAMNL";

  public WechatClient(String name, String queueName) {
    super(name, queueName, "wechat");
  }

  private void sendWechat(String toUser, String content) {
    int i;
    TemplateMsg tm = new TemplateMsg();
    Map<String, Object> dataMap = new HashMap<String, Object>();

    String[] fieldSplit = content.split("\n");
    for (i = 0; i < WX_TEMPLATE_KEY.length; i++) {
      TemplateMsgField tmf = new TemplateMsgField(fieldSplit[i], "#173177");
      dataMap.put(WX_TEMPLATE_KEY[i], tmf);
    }

    tm.setTouser(toUser);
    tm.setTemplate_id(WX_TEMPLATE_ID);
    tm.setUrl("http://61.48.51.134/login");
    tm.setData(dataMap);
    System.out.println(JSON.toJSONString(tm));
  }

  @Override
  protected int send(String message) {
    try {
      QueueMessage queueMessage = JSON.parseObject(message, QueueMessage.class);
      List<String> toUserList = queueMessage.getReceivers();

      for (String toUser: toUserList) {
        sendWechat(toUser, queueMessage.getContent());
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
