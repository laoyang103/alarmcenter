package cn.junety.alarm.web.interceptor;

import cn.junety.alarm.base.entity.User;
import cn.junety.alarm.base.util.WechatUtil;
import cn.junety.alarm.web.service.UserLoginStatusService;
import cn.junety.alarm.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by caijt on 2017/4/2.
 *
 * 对未登陆的使用者进行拦截
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter {

  private static final Logger logger = LoggerFactory.getLogger(UserLoginInterceptor.class);

  @Autowired
  private UserService userService;
  @Autowired
  private UserLoginStatusService userLoginStatusService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    String identification = userLoginStatusService.getIdentificationFromLoginStatus(request);
    User user = userService.getUserByIdentification(identification);

    String phys = request.getParameter("phys");
    String code = request.getParameter("code");
    String wxopenid = request.getParameter("wxopenid");
    logger.info("get wechat by code: " + code);
    if (null == user && (null != code || null != wxopenid)) {
      if (null != code && null == wxopenid) {
        wxopenid = WechatUtil.getWxopenidByCode(code);
      }
      user = userService.getUserByWechat(wxopenid);
      logger.info("get user by wechat: " + user);
      if (null != user) {
        userLoginStatusService.addLoginStatus(request, user.getIdentification());
      }
    }

    if (user == null) {
      logger.debug("login status check fail, identification:{}", identification);
      if (null != wxopenid && null != phys) {
        request.setAttribute("wxopenid", wxopenid);
        request.setAttribute("phys", phys);
        return true;
      } else if (null != wxopenid) {
        response.sendRedirect("/wxregister?" + "wxopenid=" + wxopenid);
      } else {
        response.sendRedirect("/login");
      }
      return false;
    }

    request.setAttribute("user", user);
    return true;
  }
}
