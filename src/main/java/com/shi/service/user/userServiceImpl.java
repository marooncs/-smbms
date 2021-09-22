package com.shi.service.user;

import com.shi.dao.user.userDao;
import com.shi.dao.user.userDaoImpl;
import com.shi.pojo.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class userServiceImpl implements userService{
    private userDao userdao;
    public userServiceImpl(){
        userdao = new userDaoImpl();
    }

    public User login(String username, String password){
        User user = userdao.getLoginUser(username);
        if(user != null && user.getUserPassword().equals(password)){
            // 登录成功
            return user;
        }else{
            // 用户名或密码错误
            return null;
        }
    }

    // 得到查询的用户数量
    public int getUserCount(String username, int userRole) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("userRole", userRole);
        return userdao.getUserCount(map);
    }

    public List<User> getUserList(String username, int userRole, int CurrentPageNo, int PageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("userRole", userRole);
        map.put("CurrentPageNo", (CurrentPageNo - 1) * PageSize);
        map.put("PageSize",PageSize);
        return userdao.getUserList(map);
    }

    public User userCodeExist(String userCode) {
        return userdao.getLoginUser(userCode);
    }

    public boolean addUser(User user) {
        boolean flag = false;
        int updateRows = userdao.addUser(user);
        if(updateRows > 0){
            flag = true;
        }
        return flag;
    }


    public boolean updateUserPwd(int id, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("password", password);
        int i = new userDaoImpl().updateUserPwd(map);
        if(i > 0){
            return true;
        }else{
            return false;
        }
    }

    public User getUserById(int id) {
        return userdao.getUserById(id);
    }

    public boolean modifyUser(User user) {
        boolean flag = false;
        int updateRows = userdao.modifyUser(user);
        if(updateRows > 0){
            flag = true;
        }
        return flag;
    }

    public boolean delUser(int id) {
        boolean flag = false;
        int updateRows = userdao.delUser(id);
        if(updateRows >= 0){
            flag = true;
        }
        return flag;
    }
}
