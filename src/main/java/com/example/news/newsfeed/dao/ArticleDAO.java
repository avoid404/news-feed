package com.example.news.newsfeed.dao;

import com.example.news.newsfeed.base.mongo.MongoAbstractDAO;
import com.example.news.newsfeed.model.Article;

import org.springframework.stereotype.Service;

@Service
public class ArticleDAO extends MongoAbstractDAO<Article> {

    @Override
    protected Class<Article> getEntityClass() {
        return Article.class;
    }

    @Override
    protected int pageSize() {
        return 10;
    }
    
}
