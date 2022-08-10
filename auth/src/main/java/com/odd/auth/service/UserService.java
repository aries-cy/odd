package com.odd.auth.service;

import com.odd.auth.repostory.UserRepository;
import com.odd.common.entity.User;
import com.odd.common.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<UserRepository> {

    public List<User> list(){
        return respository.findAll();
    }
}
