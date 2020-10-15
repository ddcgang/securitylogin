package com.demo.securitylogin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdminController {
    @RequestMapping("/")
    @PreAuthorize("hasPermission('/','r')")
    public String index() {
        return "/admin/index";
    }
}
