package com.demo.securitylogin.service.impl.sys;

import com.demo.securitylogin.dao.sys.UserRoleMapper;
import com.demo.securitylogin.model.sys.UserRole;
import com.demo.securitylogin.service.sys.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserRoleImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> findByUserName(String userName) {
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userName);
        return userRoleMapper.selectByExample(example);
    }
}
