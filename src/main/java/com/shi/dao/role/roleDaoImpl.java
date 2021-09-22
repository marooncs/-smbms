package com.shi.dao.role;

import com.shi.Utiles.mybatis_utils;
import com.shi.pojo.Role;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class roleDaoImpl implements roleDao {
    @Override
    public List<Role> getRoleList() {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        roleDao mapper = sqlSession.getMapper(roleDao.class);
        List<Role> roleList = mapper.getRoleList();
        return roleList;
    }
}
