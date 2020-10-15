package com.demo.securitylogin.service.impl.sys;

import com.demo.securitylogin.dao.sys.RoleMapper;
import com.demo.securitylogin.model.sys.Role;
import com.demo.securitylogin.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role findById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
