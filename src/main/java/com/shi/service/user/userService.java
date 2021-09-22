package com.shi.service.user;

import com.shi.pojo.User;

import java.util.List;
import java.util.Map;

public interface userService {
    User login(String username, String password);
    int getUserCount(String username, int userRole);
    List<User> getUserList(String username, int userRole, int CurrentPageNo, int PageSize);
    User userCodeExist(String userCode);
    boolean addUser(User user);
    boolean updateUserPwd(int id, String password);
    User getUserById(int id);
    boolean modifyUser(User user);
    boolean delUser(int id);
}
