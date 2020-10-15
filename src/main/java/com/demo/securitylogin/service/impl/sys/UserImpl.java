package com.demo.securitylogin.service.impl.sys;

import com.demo.securitylogin.dao.sys.UserMapper;
import com.demo.securitylogin.model.sys.User;
import com.demo.securitylogin.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Integer insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer update(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User findByName(String userName) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userName);
        return userMapper.selectOneByExample(example);
    }
}
