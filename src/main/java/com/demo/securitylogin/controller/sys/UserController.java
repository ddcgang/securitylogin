package com.demo.securitylogin.controller.sys;

import com.demo.securitylogin.model.sys.User;
import com.demo.securitylogin.model.util.ResultUtil;
import com.demo.securitylogin.service.sys.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("sys/user")
@Api(tags = "人员接口",description="人员文档说明",hidden=true)
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("index")
    public String index() {
        return "sys/user/index";
    }

    @GetMapping("find")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "账号",required = true,dataType = "String")
    })
    @ApiOperation("根据账号查询用户信息")
    //@PreAuthorize("hasPermission('/sys/user/find','r')")
    public ResultUtil find(String userName) {
        return new ResultUtil(userService.findByName(userName));
    }

    @PostMapping("add")
    @ResponseBody
    public ResultUtil add(User user) {
        userService.insert(user);
        return new ResultUtil();
    }


}
