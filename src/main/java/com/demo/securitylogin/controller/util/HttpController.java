package com.demo.securitylogin.controller.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.securitylogin.controller.BaseController;
import com.demo.securitylogin.model.util.ResultUtil;
import com.demo.securitylogin.util.HttpClientUtil;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("util/http")
public class HttpController extends BaseController {

    /**
     * post body raw->json响应
     */
    @PostMapping("add")
    public ResultUtil add(@RequestBody Map<String, Object> map, String id) {
        System.out.println("-------------------[Raw-Json]-----------------------");
        Set<String> set = map.keySet();
        for (String s : set) {
            System.out.println(s + "->" + map.get(s));
        }
        System.out.println(JSON.toJSONString(map));
        System.out.println("-------------------[header]-----------------------");
        System.out.println(request.getHeader("token"));
        System.out.println(request.getHeader("sign"));
        System.out.println("-------------------[url]-----------------------");
        System.out.println(id);
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("r", "success");
        return new ResultUtil(mapResult);
    }

    /**
     * post body form-data 带文件响应
     *
     * @param f2
     * @return
     */
    @PostMapping("addForm")
    public ResultUtil addForm(@RequestParam("f2") MultipartFile f2) {
        System.out.println("-------------------[form-data]-----------------------");
        System.out.println(request.getParameter("f1"));
        System.out.println("文件名称：" + f2.getOriginalFilename());
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("r", "success");
        return new ResultUtil(mapResult);
    }

    /**
     * post body form-data 不带文件响应
     *
     * @return
     */
    @PostMapping("addForm1")
    public ResultUtil addForm1() {
        System.out.println("-------------------[form1-data]-----------------------");
        System.out.println(request.getParameter("f1"));
        System.out.println(request.getParameter("id"));
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("r", "success");
        return new ResultUtil(mapResult);
    }

    /**
     * post body x-www-form-urlencoded 响应
     *
     * @return
     */
    @PostMapping("addWWW")
    public ResultUtil addWWW() {
        System.out.println("-------------------[x-www-form-urlencoded]-----------------------");
        System.out.println(request.getParameter("x1"));
        System.out.println(request.getParameter("x2"));
        System.out.println("-------------------[header]-----------------------");
        System.out.println(request.getHeader("token"));
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("r", "success");
        return new ResultUtil(mapResult);
    }

    @GetMapping("send")
    public void send() {
        Map<String, Object> map = new HashMap<>();
        map.put("x1", "x1 success成功！@#￥%……&*-！");
        map.put("x2", "x2 123456");
        map.put("f1", "f1 123456");
        map.put("strname", "杭州");
        Map<String, String> header = new HashMap<>();
        header.put("token", UUID.randomUUID().toString());
        header.put("sign", UUID.randomUUID().toString());
        map.put("h1", header);
        //String result = HttpClientUtil.httpRequest("http://127.0.0.1:9100/util/http/addWWW", "post", map,null);
        //String result = HttpClientUtil.httpRequest("http://127.0.0.1:9100/util/http/addForm1?id=456789", "post", map,null);
        //String result = HttpClientUtil.httpRequest("http://127.0.0.1:9100/util/http/add?id=456789", "postJson", map, header);
        //String result = HttpClientUtil.httpRequest("http://127.0.0.1:9100/util/http/addForm1?id=456789", "post", map, header);
        String result = HttpClientUtil.httpRequest("http://127.0.0.1:9100/util/http/addWWW?id=456789", "post", map, header);
        System.out.println(result);
    }
}
