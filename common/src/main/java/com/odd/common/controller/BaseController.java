package com.odd.common.controller;

import com.odd.common.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

public class BaseController<S extends BaseService<?, ?>> {

    @Resource
    protected S service;

    @GetMapping("list")
    public List<?> listAll() {
        return service.listAll();
    }

    @GetMapping("page")
    public Page<?> page(@RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"));
        return service.page(pageRequest);
    }
}
