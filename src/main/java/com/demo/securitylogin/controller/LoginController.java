package com.demo.securitylogin.controller;

import com.demo.securitylogin.util.SerialNumberUtil;
import com.demo.securitylogin.util.VerificationCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.IOException;

@RequestMapping("login")
@Controller
@Api(tags = "登录")
public class LoginController extends BaseController {
    @GetMapping("index")
    public String index() {
        return "/login/index";
    }

    @GetMapping("code")
    @ResponseBody
    @ApiParam("验证码")
    public void randCode() throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //生成随机字串
        String verifyCode = SerialNumberUtil.getRandomCode(3, 4, "");
        //存入会话session
        request.getSession().setAttribute("VER_CODE", verifyCode.toLowerCase());
        VerificationCodeUtil.outputImage(100, 38, response.getOutputStream(), verifyCode);
    }
}
