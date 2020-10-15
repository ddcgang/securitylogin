package com.demo.securitylogin.service.impl.sys;

import com.demo.securitylogin.dao.sys.RoleModuleMapper;
import com.demo.securitylogin.model.sys.Module;
import com.demo.securitylogin.model.sys.RoleModule;
import com.demo.securitylogin.service.sys.RoleModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleModuleImpl implements RoleModuleService {
    @Autowired
    RoleModuleMapper roleModuleMapper;

    @Override
    public RoleModule findById(Integer id) {
        return roleModuleMapper.selectByPrimaryKey(id);
    }
}
