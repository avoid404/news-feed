package com.example.news.newsfeed.service;

import java.util.List;

import com.example.news.newsfeed.model.Article;
import com.example.news.newsfeed.model.TechCrunchResponse;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TechCrunchService {
    
    private final RestTemplate restTemplate;

    public TechCrunchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Article> getArticles(String url) {
        return restTemplate.getForObject(url, TechCrunchResponse.class).getArticles();
    }
}
