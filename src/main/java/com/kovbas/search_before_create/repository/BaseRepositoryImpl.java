package com.kovbas.search_before_create.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;

abstract public class BaseRepositoryImpl<T, ID extends Serializable> implements BaseRepository<T, ID> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
