package com.example.news.newsfeed.base.mongo;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

public interface IBaseDAO<T> {
    T findById(String id);

    T find(Criteria criteria);

    List<T> findAll(Criteria criteria);

    List<T> findAll();

    List<T> findAllByPage(Integer pageSize, Integer pageNumber, Sort.Direction order, String sortingProperty);

    T insert(T entity);

    void update(String id, Map<String, Object> params);

    // List<T> findAll(Map<String, Object> params, Map<String, Object> neParams, Map<String, List<?>> paramsIn, Map<String, Boolean> existsParams, Integer page, Sort.Direction order, Integer pageSize, String sortProperty);

    void upsert(Criteria criteria, T entity, Map<String, Object> updateParams);
}
