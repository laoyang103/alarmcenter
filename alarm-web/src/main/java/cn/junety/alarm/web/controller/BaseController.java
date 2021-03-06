package cn.junety.alarm.web.controller;

import cn.junety.alarm.base.entity.User;
import cn.junety.alarm.web.service.UserLoginStatusService;
import cn.junety.alarm.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by caijt on 2017/4/2.
 * 用户信息处理
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;
    @Autowired
    UserLoginStatusService userLoginStatusService;

    User getUser(HttpServletRequest request) {
        Object user = request.getAttribute("user");
        return (User) user;
    }
}
