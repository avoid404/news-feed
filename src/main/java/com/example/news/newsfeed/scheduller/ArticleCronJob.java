package com.example.news.newsfeed.scheduller;

import java.util.List;

import com.example.news.newsfeed.model.Article;
import com.example.news.newsfeed.repository.ArticleRepository;
import com.example.news.newsfeed.service.TechCrunchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ArticleCronJob {
    
    @Value("${newsfeed.techcrunch.url}")
    private String getTechCrunchArticles;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TechCrunchService articleService;
    
    @Scheduled(fixedRate = 600000)
    public void saveArticles() {
        System.out.println("Running cron job");
        List<Article> articles = articleService.getArticles(getTechCrunchArticles);
        // articleRepository.deleteAll();

        for(Article article: articles) {
            try {
                articleRepository.save(article);
            }
            catch(DuplicateKeyException e) {
                System.out.println("url already present for author: " + article.getAuthor());
            }
        };
        // articleRepository.saveAll(articles);
    }

    public ArticleCronJob() {
        System.out.println("cron job constructor");
    }
}
