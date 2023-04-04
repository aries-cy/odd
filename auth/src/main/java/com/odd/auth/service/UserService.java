package com.odd.auth.service;

import com.odd.auth.repostory.UserRepository;
import com.odd.common.entity.User;
import com.odd.common.service.BaseService;
import org.springframework.stereotype.Service;


@Service
public class UserService extends BaseService<UserRepository, User> {

}
