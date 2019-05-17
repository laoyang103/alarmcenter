package cn.junety.alarm.web.service;

import cn.junety.alarm.base.entity.User;
import cn.junety.alarm.base.entity.Project;
import cn.junety.alarm.base.entity.Group;
import cn.junety.alarm.base.entity.Alarm;
import cn.junety.alarm.base.entity.Module;
import cn.junety.alarm.base.entity.UserTypeEnum;
import cn.junety.alarm.web.dao.UserDao;
import cn.junety.alarm.web.vo.UserSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by caijt on 2017/4/2.
 * 处理用户
 */
@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private AlarmService alarmService;

    /**
     * 根据用户账号获取用户信息
     * @param account 用户账号
     * @return 用户信息
     */
    public User getUserByAccount(String account) {
        try {
            return userDao.getUserByExactAccount(account);
        } catch (Exception e) {
            logger.error("get by account error, account:{}, caused by", account, e);
            return null;
        }
    }

    /**
     * 根据用户标识获取用户信息
     * @param identification 用户标识
     * @return 用户信息
     */
    public User getUserByIdentification(String identification) {
        try {
            return userDao.getUserByIdentification(identification);
        } catch (Exception e) {
            logger.error("get by identification error, identification:{}, caused by", identification, e);
            return null;
        }
    }

    /**
     * 根据用户标识获取用户信息
     * @param wechat 用户标识
     * @return 用户信息
     */
    public User getUserByWechat(String wechat) {
        try {
            return userDao.getUserByWechat(wechat);
        } catch (Exception e) {
            logger.error("get by wechat error, wechat:{}, caused by", wechat, e);
            return null;
        }
    }

    /**
     * 获取用户列表
     * @param userSearch 查询参数
     * @return 用户列表
     */
    public List<User> getUserList(UserSearch userSearch) {
        if(userSearch.getName() != null) {
            return userDao.getUserByName(userSearch);
        } else if(userSearch.getAccount() != null) {
            return userDao.getUserByAccount(userSearch);
        } else {
            return userDao.getUser(userSearch);
        }
    }

    /**
     * 获取用户列表的总长度，用于分页
     * @param userSearch 查询参数
     * @return 用户列表的总长度
     */
    public int getUserCount(UserSearch userSearch) {
        if(userSearch.getName() != null) {
            return userDao.getUserCountByName(userSearch);
        } else if(userSearch.getAccount() != null) {
            return userDao.getUserCountByAccount(userSearch);
        } else {
            return userDao.getUserCount();
        }
    }

    /**
     * 根据用户id获取用户信息
     * @param id 用户id
     * @return 用户信息
     */
    public User getUser(int id) {
        return userDao.getUserById(id);
    }

    /**
     * 创建用户
     * @param user 用户信息
     */
    public int createUser(User user) {
        // 添加用户
        user.setType(UserTypeEnum.NORMAL_USER.value());
        user.setIdentification(UUID.randomUUID().toString());
        userDao.save(user);
        int userId = userDao.getUserByExactAccount(user.getAccount()).getId();
        System.out.println(userId);

        // 默认项目
        Project project = new Project();
        project.setName(user.getAccount() + "的默认项目");
        project.setIp(0);
        project.setPort(5144);
        project.setComment(user.getAccount() + "的默认项目");
        int projectId = projectService.createProject(user, project);

        // 项目和用户关联
        projectMemberService.addProjectMember(projectId, userId, 0);

        // 默认模块
        Module module = new Module();
        module.setProjectId(projectId);
        module.setName(user.getAccount() + "的默认模块");
        int moduleId = moduleService.createModule(module);

        // 默认接收组
        int groupId = groupService.createGroup(projectId, user.getAccount() + "的默认接收组");
        groupService.addGroupMember(groupId, userId);

        // 默认告警机制
        Alarm alarm = new Alarm();
        alarm.setName(user.getAccount() + "的默认告警机制");
        alarm.setProjectId(projectId);
        alarm.setModuleId(moduleId);
        alarm.setGroupId(groupId);
        alarm.setRouteKey("com.alarm.*");
        alarm.setConfig("{\"freq_limit\":true,\"mail\":false,\"wechat\":true,\"sms\":false,\"qq\":false,\"debug_interval\":5,\"debug_times\":10,\"info_interval\":5,\"info_times\":10,\"error_interval\":5,\"error_times\":10}");
        alarmService.createAlarm(alarm);

        return userId;
    }

    /**
     * 更新用户信息
     * @param user 用户信息
     */
    public void updateUser(User user) {
        userDao.update(user);
    }

    /**
     * 根据用户id删除用户
     * @param id 用户id
     */
    public void deleteUser(int id) {
        userDao.delete(id);
    }
}
