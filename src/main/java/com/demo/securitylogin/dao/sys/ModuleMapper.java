package com.demo.securitylogin.dao.sys;

import com.demo.securitylogin.model.sys.Module;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ModuleMapper extends Mapper<Module> {

    @Select("SELECT DISTINCT m.* FROM sys_module m " +
            "LEFT JOIN sys_role_module rm ON m.id=rm.`module_id` " +
            "LEFT JOIN sys_role r ON r.`id`=rm.`role_id` " +
            "LEFT JOIN sys_user_role ur ON ur.`role_auth`=r.`role_auth` " +
            "LEFT JOIN sys_user u ON u.`user_name`=ur.`user_name` " +
            "WHERE u.`user_name`='${userName}'")
    List<Module> findByUserName(String userName);
}
