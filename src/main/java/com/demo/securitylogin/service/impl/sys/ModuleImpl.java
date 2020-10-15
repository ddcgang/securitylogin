package com.demo.securitylogin.service.impl.sys;

import com.demo.securitylogin.dao.sys.ModuleMapper;
import com.demo.securitylogin.model.sys.Module;
import com.demo.securitylogin.model.util.ExampleUtil;
import com.demo.securitylogin.service.sys.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleImpl implements ModuleService {
    @Autowired
    ModuleMapper moduleMapper;

    @Override
    public List<Module> findAll() {
        return moduleMapper.selectAll();
    }

    private Example example(Map<String, Map<String, Object>> map) {
        return null;
    }
}
