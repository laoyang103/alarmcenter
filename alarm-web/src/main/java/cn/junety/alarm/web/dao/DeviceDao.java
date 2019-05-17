package cn.junety.alarm.web.dao;

import cn.junety.alarm.base.entity.User;
import cn.junety.alarm.base.entity.Device;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by caijt on 2017/4/2.
 * 用户dao
 */
public interface DeviceDao {

  @Select("select * from tb_device where userid=#{user.id}")
  List<Device> getDeviceByUser(User user);

  @Insert("insert into tb_device(userid, cpus, macs) values(#{userid}, #{cpus}, #{macs})")
  int save(Device device);

  @Update("update tb_device set userid=#{userid}, macs=#{macs}, cpus=#{cpus}")
  int update(Device device);

  @Delete("delete from tb_device where id=#{id}")
  int delete(int id);
}
