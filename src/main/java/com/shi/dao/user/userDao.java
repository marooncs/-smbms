package com.shi.dao.user;

import com.shi.pojo.User;

import java.util.List;
import java.util.Map;

public interface userDao {
    User getLoginUser(String userCode); // 用户登录查询

    // 根据用户名或者角色查询用户总数
    int getUserCount(Map map);

    // 根据用户名或者角色查询用户
    List<User> getUserList(Map map);

    // 更新密码
    int updateUserPwd(Map map);

    // 添加用户
    int addUser(User user);

    User getUserById(int id);

    int modifyUser(User user);

    int delUser(int id);

}
