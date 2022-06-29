package com.study.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 描述
 *
 * @author cy
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test(){
        return "success";
    }
}
