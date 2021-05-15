package com.example.news.newsfeed.base.mongo;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;

public abstract class MongoAbstractDAO<T> implements IBaseDAO<T> {
    @Autowired
    private MongoTemplate mongoTemplate;

    protected abstract Class<T> getEntityClass();

    protected abstract int pageSize();

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    @Override
    public T find(Criteria criteria) {
        try {
            return mongoTemplate.findOne(new Query(criteria), getEntityClass());
        }
        catch(Exception e) {
            System.out.format("Excpetion in finding document for criteria for class::%s\n", getEntityClass());
        }
        return null;
    }

    @Override
    public List<T> findAll(Criteria criteria) {
        try {
            return mongoTemplate.find(new Query(criteria), getEntityClass());
        }
        catch(Exception e) {
            System.out.format("Excpetion in finding documents for criteria for class::%s\n", getEntityClass());
        }
        return null;
    }

    

    @Override
    public List<T> findAll() {
        try {
            return mongoTemplate.find(new Query(), getEntityClass());
        }
        catch(Exception e) {
            System.out.format("Excpetion in finding documents for criteria for class::%s\n", getEntityClass());
        }
        return null;
    }

    @Override
    public T findById(String id) {
        try {
            return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), getEntityClass());
        }
        catch(Exception e) {
            System.out.format("Excpetion in finding by id %s for class::%s\n ", id, getEntityClass());
        }
        return null;
    }

    @Override
    public T insert(T entity) {
        try {
            return mongoTemplate.save(entity);
        }
        catch(Exception e) {
            System.out.format("Excpetion in finding for criteria for class::%s\n", getEntityClass());
        }
        return null;
    }

    @Override
    public void update(String id, Map<String, Object> params) {
        try {
            Update update = new Update();

            params.forEach(update::set);
            mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), update, getEntityClass());
        }
        catch(Exception e) {
            System.out.format("Cannot update params for id%s for class::%s\n", id, getEntityClass());
        }
    }

    @Override
    public void upsert(Criteria criteria, T entity, Map<String, Object> updateParams) {
        try {
            T document = find(criteria);

            if(document != null) {
                if(updateParams != null) {
                    Update update = new Update();
                    updateParams.forEach(update::set);
                    mongoTemplate.updateFirst(new Query(criteria), update, getEntityClass());
                }
            }
            else {
                // insert
                insert(entity);
            }
        }
        catch(Exception e) {
            System.out.format("Cannot save new entity for class::%s\n", getEntityClass());
        }
        
    }

    @Override
    public Page<T> findAllByPage(Integer pageSize, Integer pageNumber, Direction order, String sortingProperty) {
        try {
            List<Sort.Order> orderList = Collections.singletonList(new Sort.Order(order, sortingProperty));
            Query query = new Query().with(Sort.by(orderList)).with(PageRequest.of(pageNumber, pageSize));
            List<T> records =  mongoTemplate.find(query, getEntityClass()); //Test this query


            return PageableExecutionUtils.getPage(
                records,
                PageRequest.of(pageNumber, pageSize),
                () -> mongoTemplate.count(new Query().with(Sort.by(orderList)), getEntityClass())); 
        }
        catch(Exception e) {
            System.out.format("Cannot find data for page %d for class::%s\n", pageNumber, getEntityClass());
        }
        return null;
    }
    


    
}
