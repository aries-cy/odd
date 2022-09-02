package com.odd.auth.controller;

import com.odd.annotation.ResponseResult;
import com.odd.auth.service.UserService;
import com.odd.common.controller.BaseController;
import com.odd.common.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController extends BaseController<UserService> {



    @ResponseResult
    @GetMapping("list")
    public List<User> list(){
        return service.list();
    }


    @GetMapping("test")
    public String test(){
        return "嗨害嗨";
    }

}
