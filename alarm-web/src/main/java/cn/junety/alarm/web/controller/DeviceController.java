package cn.junety.alarm.web.controller;

import cn.junety.alarm.base.entity.Device;
import cn.junety.alarm.web.common.ResponseHelper;
import cn.junety.alarm.web.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * Created by caijt on 2017/4/16.
 * 用户API
 */
@RestController
public class DeviceController extends BaseController {

  @Autowired
  private DeviceService deviceService;

  @RequestMapping(value = "/addDevice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public String addDevice(HttpServletRequest request, @RequestBody Device device) {
    try {
      deviceService.save(device);
      return ResponseHelper.buildResponse(2000);
    } catch (Exception e) {
      logger.error("create device error, device:{}, caused by", device, e);
      return ResponseHelper.buildResponse(5000, "reason", "系统出错");
    }
  }
}
