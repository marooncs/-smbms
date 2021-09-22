package com.shi.service.role;

import com.shi.dao.role.roleDaoImpl;
import com.shi.pojo.Role;

import java.util.List;

public class roleServiceImpl implements roleService {
    @Override
    public List<Role> getRoleList() {
        return new roleDaoImpl().getRoleList();
    }
}
