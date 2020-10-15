package com.demo.securitylogin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdminController extends BaseController {
    @RequestMapping("/")
    public String index(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.getSession().setAttribute("username", user.getUsername());
        model.addAttribute("username",user.getUsername());
        model.addAttribute("auths",user.getAuthorities());
        return "/admin/index";
    }
}
