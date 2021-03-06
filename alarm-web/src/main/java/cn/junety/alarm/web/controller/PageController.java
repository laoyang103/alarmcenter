package cn.junety.alarm.web.controller;

import cn.junety.alarm.base.entity.User;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by caijt on 2017/4/3.
 * 页面跳转API
 */
@Controller
public class PageController extends BaseController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView toLoginPage(HttpServletRequest request, Model model) {
        String wxopenid = request.getParameter("wxopenid");
        model.addAttribute("wxopenid", wxopenid);
        logger.info("login wxopenid: " + wxopenid);
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView toRegisterPage(HttpServletRequest request, Model model) {
        String wxopenid = request.getParameter("wxopenid");
        model.addAttribute("wxopenid", wxopenid);
        logger.info("register wxopenid: " + wxopenid);
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getAlarms(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        logger.info("GET /home, current_user:{}", currentUser);

        model.addAttribute("current_user", currentUser);

        return new ModelAndView("home");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView toUserPage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        logger.info("GET /user, current_user:{}", currentUser);

        model.addAttribute("current_user", currentUser);

        return new ModelAndView("user");
    }

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public ModelAndView toProjectPage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        logger.info("GET /project, current_user:{}", currentUser);

        model.addAttribute("current_user", currentUser);

        return new ModelAndView("project");
    }

    @RequestMapping(value = "/module", method = RequestMethod.GET)
    public ModelAndView toModulePage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        logger.info("GET /module, current_user:{}", currentUser);

        model.addAttribute("current_user", currentUser);

        return new ModelAndView("module");
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public ModelAndView toProjectMemberPage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        logger.info("GET /member, current_user:{}", currentUser);

        model.addAttribute("current_user", currentUser);

        return new ModelAndView("member");
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public ModelAndView toGroupPage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        logger.info("GET /group, current_user:{}", currentUser);

        model.addAttribute("current_user", currentUser);

        return new ModelAndView("group");
    }

    @RequestMapping(value = "/alarm", method = RequestMethod.GET)
    public ModelAndView toAlarmPage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        logger.info("GET /alarm, current_user:{}", currentUser);

        model.addAttribute("current_user", currentUser);

        return new ModelAndView("alarm");
    }

    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public ModelAndView toLogPage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        logger.info("GET /log, current_user:{}", currentUser);

        model.addAttribute("current_user", currentUser);

        return new ModelAndView("log");
    }

    @RequestMapping(value = "/wxbind", method = RequestMethod.GET)
    public ModelAndView toWxbindPage(HttpServletRequest request, Model model) {
      String wxopenid = request.getParameter("wxopenid");
      logger.info("GET /wxbind, wxopenid:{}", wxopenid);

      model.addAttribute("from_wxopenid", wxopenid);

      return new ModelAndView("wxbind");
    }
}
