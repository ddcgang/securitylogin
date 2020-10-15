package com.demo.securitylogin.security;


import com.demo.securitylogin.model.sys.Module;
import com.demo.securitylogin.service.sys.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Component
public class MyPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    ModuleService moduleService;

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        User user = (User) authentication.getPrincipal();
        List<Module> moduleList = moduleService.findByUserName(user.getUsername());
        for (Module module : moduleList) {
            //System.out.println(o);// /sys/user/find
            //System.out.println(o1);// r
            if (module.getUrl().equals(o.toString())) return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
