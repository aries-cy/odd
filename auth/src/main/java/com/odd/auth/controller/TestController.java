package com.odd.auth.controller;

import com.odd.annotation.ExecuteLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/executeLog")
    @ExecuteLog(executeTime = true, param = true, result = true)
    public String executeLog(@RequestParam String param){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return param + "-success";
    }
}
