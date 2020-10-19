package com.demo.securitylogin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Api(tags = "Admin-管理员后台")
public class AdminController extends BaseController {
    @RequestMapping("/")
    @ApiOperation("列表")
    public String index(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.getSession().setAttribute("username", user.getUsername());
        model.addAttribute("username",user.getUsername());
        model.addAttribute("auths",user.getAuthorities());
        return "/admin/index";
    }
}
