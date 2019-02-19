package cn.junety.alarm.web.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.junety.alarm.base.entity.User;
import cn.junety.alarm.base.entity.UserTypeEnum;
import cn.junety.alarm.web.service.WxsvcService;
import cn.junety.alarm.web.vo.WxbindForm;
import cn.junety.alarm.web.common.MessageUtil;
import cn.junety.alarm.web.common.ResponseHelper;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by caijt on 2017/4/3.
 * 页面跳转API
 */
@RestController
public class WxsvcController extends BaseController {

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

  @RequestMapping(value = "/wxbind", method = RequestMethod.POST)
  public String wxbind(HttpServletRequest request, @RequestBody WxbindForm wxbindForm) {
    logger.info("POST /wxbind, wxbindForm:{}", JSON.toJSONString(wxbindForm));

    User user = userService.getUserByAccount(wxbindForm.getUsername());
    if (user != null && (UserTypeEnum.ADMIN_USER.value().equals(user.getType())
          || UserTypeEnum.NORMAL_USER.value().equals(user.getType()))) {
      wxsvcService.bindWxOpenid(user, wxbindForm.getWxopenid());
      userLoginStatusService.addLoginStatus(request, user.getIdentification());
      return ResponseHelper.buildResponse(2000, "status", "success");
    }
    return ResponseHelper.buildResponse(4004, "reason", "invalid username or password");
  }
}
