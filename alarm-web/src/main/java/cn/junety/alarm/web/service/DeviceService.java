package cn.junety.alarm.web.service;

import cn.junety.alarm.base.entity.User;
import cn.junety.alarm.base.entity.Device;
import cn.junety.alarm.web.dao.UserDao;
import cn.junety.alarm.web.dao.DeviceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
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

  @Autowired
  private UserDao userDao;

  private int genSysinfoFile(Device device, String filePath) {
    int i;
    String userInfoStr = "", sysInfoStr = "";

    User user = userDao.getUserById(device.getUserid());
    userInfoStr += "\r\nuserName=" + user.getAccount();
    userInfoStr += "\r\ncontacts=" + user.getName();
    userInfoStr += "\r\ntelephone=" + user.getPhone();
    userInfoStr += "\r\nemail=" + user.getMail();

    String cpus = device.getCpus();
    String[] cpuArray = cpus.split("_");
    for (i = 0; i < cpuArray.length; i++) {
      sysInfoStr += "\r\ncpuid" + i + "=" + cpuArray[i];
    }
    String macs = device.getMacs();
    String[] macArray = macs.split("_");
    for (i = 0; i < macArray.length; i++) {
      sysInfoStr += "\r\n" + macArray[i];
    }
    sysInfoStr += "\r\nproductNo=" + "";

    try {
      File devinfoFile = new File(filePath);
      if (!devinfoFile.exists()) {
        devinfoFile.createNewFile();
      }
      FileWriter fileWritter = new FileWriter(devinfoFile.getName(), true);
      fileWritter.write(userInfoStr + device.getModString() + sysInfoStr);
      fileWritter.close();
      return 0;
    }catch(IOException e){
      e.printStackTrace();
      return 1;
    }
  }

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
