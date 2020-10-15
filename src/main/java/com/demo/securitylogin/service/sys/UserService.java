package com.demo.securitylogin.service.sys;

import com.demo.securitylogin.model.sys.User;

public interface UserService {

    Integer insert(User user);

    Integer update(User user);

    User findByName(String userName);
}
