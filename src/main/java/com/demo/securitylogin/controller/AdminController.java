package com.demo.securitylogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdminController {
    @RequestMapping("/")
    public String index() {
        return "/admin/index";
    }
}
