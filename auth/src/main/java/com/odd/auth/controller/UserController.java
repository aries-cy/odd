package com.odd.auth.controller;

import com.odd.auth.service.UserService;
import com.odd.common.controller.BaseController;
import com.odd.common.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserService, User> {

}
