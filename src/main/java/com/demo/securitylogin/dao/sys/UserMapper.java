package com.demo.securitylogin.dao.sys;

import com.demo.securitylogin.model.sys.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserMapper extends Mapper<User> {
}
