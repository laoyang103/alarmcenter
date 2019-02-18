package cn.junety.alarm.web.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import cn.junety.alarm.web.common.MessageUtil;
import cn.junety.alarm.web.service.WxsvcService;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by caijt on 2017/4/3.
 * 页面跳转API
 */
@RestController
public class WxsvcController {

  @Autowired
    private WxsvcService wxsvcService;

  @GetMapping("/wxsvc")
  public String wxsvcGet(HttpServletRequest request) {
    String echostr = request.getParameter("echostr");
    if (null == echostr) {
      return "No param echostr";
    }
    return echostr;
  }

  @PostMapping("/wxsvc")
  public String wxsvcPost(HttpServletRequest request) {
    String retMsg;
    try {
      Map<String, String> msgMap = MessageUtil.parseXml(request);
      retMsg = wxsvcService.processMessage(msgMap);
    } catch (Exception e) {
      retMsg = "parse message error";
      e.printStackTrace();
    }
    return retMsg;
  }
}
