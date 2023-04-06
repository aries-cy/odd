package com.odd.common.service;

import com.odd.common.repository.BaseRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class BaseService<R extends BaseRepository<T>, T> {

    @Autowired
    protected R repository;

    @Autowired
    JPAQueryFactory queryFactory;

    // 查询所有
    public List<T> listAll() {
        return repository.findAll();
    }

    // 分页查询
    public Page<T> page(Example<T> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    // 根据ID查询记录
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    // 保存记录
    public T save(T entity) {
        return repository.save(entity);
    }

    // 删除记录
    public void delete(T entity) {
        repository.delete(entity);
    }
}
