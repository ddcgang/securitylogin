package com.demo.securitylogin.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("http")
public class HttpController extends BaseController {

    @PostMapping("add")
    public void add(@RequestBody Map<String, Object> map, @RequestHeader String token, String id) {
        System.out.println("-------------------[Raw-Json]-----------------------");
        Set<String> set = map.keySet();
        for (String s : set) {
            System.out.println(s + "->" + map.get(s));
        }
        System.out.println(JSON.toJSONString(map));
        System.out.println("-------------------[header]-----------------------");
        System.out.println(token);
        System.out.println("-------------------[url]-----------------------");
        System.out.println(id);
    }

    @PostMapping("addForm")
    public void addForm(@RequestParam("f2") MultipartFile f2) {
        System.out.println("-------------------[form-data]-----------------------");
        System.out.println(request.getParameter("f1"));
        System.out.println("文件名称：" + f2.getOriginalFilename());
        System.out.println("-------------------[x-www-form-urlencoded]-----------------------");
        System.out.println(request.getParameter("x1"));
        System.out.println(request.getParameter("x2"));
    }
}
