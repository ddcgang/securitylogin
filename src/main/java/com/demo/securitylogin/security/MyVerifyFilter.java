package com.demo.securitylogin.security;


import com.demo.securitylogin.model.util.ResultUtil;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyVerifyFilter extends OncePerRequestFilter {

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //验证码核验
        if ("POST".equals(httpServletRequest.getMethod()) && pathMatcher.match("/login/check", httpServletRequest.getServletPath())) {
            String verCode = httpServletRequest.getParameter("code");
            if (StringUtils.isEmpty(verCode)) {
                new ResultUtil().toJson(0, "请输入验证码", null, httpServletResponse);
                return;
            } else if (StringUtils.isEmpty(httpServletRequest.getSession().getAttribute("VER_CODE"))) {
                new ResultUtil().toJson(0, "验证码失效，请重新获取", null, httpServletResponse);
                return;
            } else if (!verCode.toLowerCase().equals(httpServletRequest.getSession().getAttribute("VER_CODE").toString().toLowerCase())) {
                new ResultUtil().toJson(0, "验证码错误", null, httpServletResponse);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
