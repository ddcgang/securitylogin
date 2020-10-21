package com.demo.securitylogin.controller;


import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.securitylogin.anno.JwtToken;
import com.demo.securitylogin.model.util.ResultUtil;
import com.demo.securitylogin.util.JwtUtil;
import com.demo.securitylogin.util.SerialNumberUtil;
import com.demo.securitylogin.util.VerificationCodeUtil;

import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("token")
    @ResponseBody
    @ApiParam("生成token")
    @JwtToken
    public ResultUtil token() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String testToken = JwtUtil.signTest();
        DecodedJWT decodedJWT = JWT.decode(testToken);
        JSONObject jsonObject = JSONObject.parseObject(decodedJWT.getClaim("data").asString());
        Map<String, Object> map = (Map) jsonObject;
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("token",testToken);
        mapResult.put("Issuer",decodedJWT.getIssuer());
        mapResult.put("Subject",decodedJWT.getSubject());
        mapResult.put("Audience",decodedJWT.getAudience().get(0));
        mapResult.put("ExpiresAt",sdf.format(decodedJWT.getExpiresAt()));
        mapResult.put("data->user",map.get("user"));
        mapResult.put("data->password",map.get("password"));
        mapResult.put("IssuedAt",sdf.format(decodedJWT.getIssuedAt()));
        mapResult.put("Id",decodedJWT.getId());
        return new ResultUtil(mapResult);
    }

    @PostMapping("tokenCheck")
    @ResponseBody
    @ApiParam("验证token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "token",required = true,dataType = "String",paramType = "header")
    })
    public String tokenCheck(@RequestHeader String token) {
        return JwtUtil.getUserId(token);
    }
}
