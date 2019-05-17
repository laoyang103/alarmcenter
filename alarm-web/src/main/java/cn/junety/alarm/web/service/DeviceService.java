package cn.junety.alarm.web.service;

import cn.junety.alarm.base.entity.User;
import cn.junety.alarm.base.entity.Device;
import cn.junety.alarm.web.dao.DeviceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by caijt on 2017/4/2.
 * 处理用户
 */
@Service
public class DeviceService {

  private Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private DeviceDao deviceDao;

  public List<Device> getDeviceByUser(User user) {
    return deviceDao.getDeviceByUser(user);
  }

  public int save(Device device) {
    return deviceDao.save(device);
  }

  public int update(Device device) {
    return deviceDao.update(device);
  }

  public int delete(int id) {
    return deviceDao.delete(id);
  }
}
