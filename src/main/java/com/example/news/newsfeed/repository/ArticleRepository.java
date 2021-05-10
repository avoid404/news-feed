package com.example.news.newsfeed.repository;

import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import com.example.news.newsfeed.model.Article;

public interface ArticleRepository extends MongoRepository<Article, String> {
    List<Article> findByAuthor(String author);
    List<Article> findAllBy(TextCriteria textCriteria);
    // List<Article> findAll(Pageable page);
}
