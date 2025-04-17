package com.odd.item.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: cao_yang
 * Date: 2025/4/17
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get")
    public String get(){
        return "success";
    }

    @GetMapping("/post")
    public JSONObject post(){
        JSONObject json = new JSONObject();
        json.put("result", "success");
        return json;
    }

}
