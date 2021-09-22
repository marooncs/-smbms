package com.shi.dao.user;

import com.shi.Utiles.mybatis_utils;
import com.shi.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class userDaoImpl implements userDao{
    public User getLoginUser(String userCode){
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        userDao mapper = sqlSession.getMapper(userDao.class);
        User loginUser = mapper.getLoginUser(userCode);
        sqlSession.close();
        return loginUser;
    }

    public int getUserCount(Map map) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        userDao mapper = sqlSession.getMapper(userDao.class);
        int userCount = mapper.getUserCount(map);
        sqlSession.close();
        return userCount;
    }

    public List<User> getUserList(Map map) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        userDao mapper = sqlSession.getMapper(userDao.class);
        List<User> userList = mapper.getUserList(map);
        sqlSession.close();
        return userList;
    }

    public int updateUserPwd(Map map) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        userDao mapper = sqlSession.getMapper(userDao.class);
        int i = mapper.updateUserPwd(map);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    public int addUser(User user) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        userDao mapper = sqlSession.getMapper(userDao.class);
        int i = mapper.addUser(user);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    public User getUserById(int id) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        userDao mapper = sqlSession.getMapper(userDao.class);
        User user = mapper.getUserById(id);
        return user;
    }

    public int modifyUser(User user) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        userDao mapper = sqlSession.getMapper(userDao.class);
        int i = mapper.modifyUser(user);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    public int delUser(int id) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        userDao mapper = sqlSession.getMapper(userDao.class);
        int i = mapper.delUser(id);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }


}
