package com.demo.securitylogin.controller.sys;

import com.demo.securitylogin.model.sys.User;
import com.demo.securitylogin.model.util.ResultUtil;
import com.demo.securitylogin.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("sys/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("index")
    public String index() {
        return "sys/user/index";
    }

    @GetMapping("find")
    @ResponseBody
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
