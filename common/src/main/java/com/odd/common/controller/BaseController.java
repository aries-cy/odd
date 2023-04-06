package com.odd.common.controller;

import com.odd.common.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
public class BaseController<S extends BaseService<?, T>, T> {

    @Resource
    protected S service;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Exception handleException(Exception e) {
        log.error("系统异常：{}", e.getMessage(), e);
        return e;
    }

    @GetMapping("list")
    public List<?> listAll() {
        return service.listAll();
    }

    @GetMapping("page")
    public Page<?> page(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int size, @RequestBody T body) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"));
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<T> example = Example.of(body, matcher);
        return service.page(example, pageRequest);
    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable("id") Long id) {
        Optional<?> optional = service.findById(id);
        return optional.orElse(null);
    }

    @PostMapping("save")
    public T save(@RequestBody T entity) {
        return service.save(entity);
    }


    @PostMapping("delete")
    public void delete(@RequestBody T entity) {
        service.delete(entity);
    }
}
