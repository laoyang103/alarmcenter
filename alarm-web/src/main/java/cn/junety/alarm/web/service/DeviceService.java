package cn.junety.alarm.web.service;

import cn.junety.alarm.base.entity.User;
import cn.junety.alarm.base.entity.Device;
import cn.junety.alarm.base.util.FileUtil;
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

  private String LICENSE_PATH = "/data/kpi/license/";

  private Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private DeviceDao deviceDao;

  @Autowired
  private UserDao userDao;

  private String mkSysinfoFile(Device device, String filePath) {
    int i;
    String userInfoStr = "", sysInfoStr = "";

    User user = userDao.getUserById(device.getUserid());
    userInfoStr += "\r\nuserName=" + device.getDevname();
    userInfoStr += "\r\ncontacts=" + user.getName();
    userInfoStr += "\r\ntelephone=" + user.getPhone();
    userInfoStr += "\r\nemail=" + user.getMail();
    userInfoStr += "\r\nvalidterm=" + device.getValidTerm();

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
      return filePath;
    }catch(IOException e){
      e.printStackTrace();
      return null;
    }
  }

  private String mkLicenseFile(String sysinfoFile, String validTerm) {
    try {
      FileUtil.getShellData("mklicense -f " + sysinfoFile + " -t " + validTerm);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public List<Device> getDeviceByUser(User user) {
    List<Device> devices = null;
    if (0 == user.getType()) {
      logger.debug("get all device info, user:{}", user);
      devices = deviceDao.getAllDevice();
    } else {
      logger.debug("get user device info, user:{}", user);
      devices = deviceDao.getDeviceByUser(user.getId());
    }
    return devices;
  }

  public Device getDeviceById(int id) {
    return deviceDao.getDeviceById(id);
  }

  public int save(Device device) {
    String infoPath, licensePath, fileName;
    fileName = LICENSE_PATH + device.getDevname() + "_" + device.getMacs() + "_" + device.getValidTerm();
    infoPath = fileName + ".INFO";
    licensePath = fileName + ".license";

    String sysinfoFile = this.mkSysinfoFile(device, infoPath);
    this.mkLicenseFile(sysinfoFile, device.getValidTerm());
    return deviceDao.save(device);
  }

  public int update(Device device) {
    return deviceDao.update(device);
  }

  public int delete(int id) {
    return deviceDao.delete(id);
  }
}
