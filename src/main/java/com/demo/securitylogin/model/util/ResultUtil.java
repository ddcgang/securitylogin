package com.demo.securitylogin.model.util;

import com.alibaba.fastjson.JSON;
import lombok.Data;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class ResultUtil implements Serializable {

    private Integer code;
    private String message;
    private Object result;

    public ResultUtil() {
        this.setCode(1);
        this.setMessage("success");
    }

    public ResultUtil(Integer code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public ResultUtil(Object result) {
        this.setCode(1);
        this.setMessage("success");
        this.setResult(result);
    }

    public ResultUtil(Integer code, String message, Object result) {
        this.setCode(code);
        this.setMessage(message);
        this.setResult(result);
    }

    public void toJson(Integer code, String message, Object result, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("message", message);
        map.put("result", result);
        response.getWriter().write(JSON.toJSONString(map));
        response.getWriter().close();
    }

}
