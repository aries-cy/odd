package com.odd.common.service;

import com.odd.common.repository.BaseRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<R extends BaseRepository> {

    @Autowired
    protected R respository;

    @Autowired
    JPAQueryFactory queryFactory;

}
