package com.example.news.newsfeed.scheduller;

import java.util.List;

import com.example.news.newsfeed.dao.ArticleDAO;
import com.example.news.newsfeed.model.Article;
import com.example.news.newsfeed.service.TechCrunchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ArticleCronJob {
    
    @Value("${newsfeed.techcrunch.url}")
    private String getTechCrunchArticles;

    @Autowired
    private TechCrunchService articleService;

    @Autowired private ArticleDAO articleDAO;
    
    @Scheduled(fixedRate = 600000)
    public void saveArticles() {
        System.out.println("Running cron job");
        List<Article> articles = articleService.getArticles(getTechCrunchArticles);
        // articleRepository.deleteAll();

        for(Article article: articles) {
            articleDAO.upsert(Criteria.where("url").is(article.getUrl()), article, null);
        };
    }

    public ArticleCronJob() {
        System.out.println("cron job constructor");
    }
}
