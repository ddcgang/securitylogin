package com.demo.securitylogin.dao.sys;

import com.demo.securitylogin.model.sys.Role;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface RoleMapper extends Mapper<Role> {
}
