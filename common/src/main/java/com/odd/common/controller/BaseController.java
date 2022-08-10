package com.odd.common.controller;

import com.odd.common.service.BaseService;

import javax.annotation.Resource;

public class BaseController<S extends BaseService> {

    @Resource
    protected S service;
}
