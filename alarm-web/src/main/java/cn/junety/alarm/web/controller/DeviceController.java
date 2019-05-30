package cn.junety.alarm.web.controller;

import cn.junety.alarm.base.entity.User;
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

  @RequestMapping(value = "/getDeviceList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public String getDeviceList(HttpServletRequest request) {
    User currentUser = getUser(request);
    try {
      logger.info("GET /getDevice, current_user:{}", currentUser);
      List<Device> deviceList = deviceService.getDeviceByUser(currentUser);

      return ResponseHelper.buildResponse(2000, "device_list", deviceList);
    } catch (Exception e) {
      logger.error("get device list error, caused by", e);
      return ResponseHelper.buildResponse(5000, "device_list", Collections.emptyList(), "device_count", 0);
    }
  }

  @RequestMapping(value = "/getDeviceById/{did}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public String getDeviceById(HttpServletRequest request, @PathVariable Integer did) {
    User currentUser = getUser(request);
    logger.info("GET /devices/{}, current_user:{}", did, currentUser);

    Device device = deviceService.getDeviceById(did);
    return ResponseHelper.buildResponse(2000, "device", device);
  }
}
