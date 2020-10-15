package com.demo.securitylogin.handler;


import com.demo.securitylogin.model.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultUtil error(Exception e) {
        return new ResultUtil(0, e.getMessage());
    }

}