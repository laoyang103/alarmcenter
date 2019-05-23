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
public class WxPageController extends BaseController {

    @RequestMapping(value = "/wxlogin", method = RequestMethod.GET)
    public ModelAndView toLoginPage(HttpServletRequest request, Model model) {
        String wxopenid = request.getParameter("wxopenid");
        model.addAttribute("wxopenid", wxopenid);
        logger.info("register wxopenid: " + wxopenid);
        return new ModelAndView("wxlogin");
    }

    @RequestMapping(value = "/wxregister", method = RequestMethod.GET)
    public ModelAndView toRegisterPage(HttpServletRequest request, Model model) {
        String wxopenid = request.getParameter("wxopenid");
        model.addAttribute("wxopenid", wxopenid);
        logger.info("register wxopenid: " + wxopenid);
        return new ModelAndView("wxregister");
    }

    @RequestMapping(value = "/wxuser", method = RequestMethod.GET)
    public ModelAndView toUserPage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        logger.info("GET /wxuser, current_user:{}", currentUser);

        model.addAttribute("current_user", currentUser);

        return new ModelAndView("wxuser");
    }

    @RequestMapping(value = "/wxproject", method = RequestMethod.GET)
    public ModelAndView toProjectPage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        logger.info("GET /wxproject, current_user:{}", currentUser);

        model.addAttribute("current_user", currentUser);

        return new ModelAndView("wxproject");
    }

    @RequestMapping(value = "/wxgroup", method = RequestMethod.GET)
    public ModelAndView toGroupPage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        logger.info("GET /wxgroup, current_user:{}", currentUser);

        model.addAttribute("current_user", currentUser);

        return new ModelAndView("wxgroup");
    }

    @RequestMapping(value = "/wxalarm", method = RequestMethod.GET)
    public ModelAndView toAlarmPage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        logger.info("GET /wxalarm, current_user:{}", currentUser);

        model.addAttribute("current_user", currentUser);

        return new ModelAndView("wxalarm");
    }

    @RequestMapping(value = "/wxalarmDetail", method = RequestMethod.GET)
    public ModelAndView toAlarmDetailPage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        String aid = request.getParameter("aid");
        logger.info("GET /wxalarmDetail, current_user:{}", currentUser);

        model.addAttribute("aid", aid);
        model.addAttribute("current_user", currentUser);

        return new ModelAndView("wxalarmDetail");
    }

    @RequestMapping(value = "/wxlog", method = RequestMethod.GET)
    public ModelAndView toLogPage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        logger.info("GET /wxlog, current_user:{}", currentUser);

        model.addAttribute("current_user", currentUser);

        return new ModelAndView("wxlog");
    }

    @RequestMapping(value = "/wxlogDetail", method = RequestMethod.GET)
    public ModelAndView toLogDetailPage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);
        String logid = request.getParameter("logid");
        logger.info("GET /wxlogDetail, current_user:{}", currentUser);

        model.addAttribute("logid", logid);
        model.addAttribute("current_user", currentUser);

        return new ModelAndView("wxlogDetail");
    }

    @RequestMapping(value = "/wxqrcode", method = RequestMethod.GET)
      public ModelAndView toQrcodePage(HttpServletRequest request, Model model) {
        User currentUser = getUser(request);

        String wxopenid = request.getParameter("wxopenid");
        String phys = request.getParameter("phys");
        String macs = phys.split("-")[0];
        String cpus = phys.split("-")[1];
        if (null != currentUser) {
          model.addAttribute("userid", currentUser.getId());
          model.addAttribute("account", currentUser.getAccount());
        }
        model.addAttribute("wxopenid", wxopenid);
        model.addAttribute("macs", macs);
        model.addAttribute("cpus", cpus);

        logger.info("wxbindPhy user: " + currentUser + "macs=" + macs + "cpus=" + cpus);

        if (null == currentUser) {
          return new ModelAndView("wxbindUserDevice");
        } else {
          return new ModelAndView("wxbindDevice");
        }
      }
}
