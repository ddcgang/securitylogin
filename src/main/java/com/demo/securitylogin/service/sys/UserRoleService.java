package com.demo.securitylogin.service.sys;

import com.demo.securitylogin.model.sys.UserRole;

import java.util.List;

public interface UserRoleService {
    List<UserRole> findByUserName(String userName);
}
