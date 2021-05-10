package com.example.news.newsfeed.controller;

import com.example.news.newsfeed.model.ArticleCatalogResponseDTO;
import com.example.news.newsfeed.service.IArticleCatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {  
    
    @Autowired
    IArticleCatalogService catalogService;

    @GetMapping("/articles/all")
    public ArticleCatalogResponseDTO listAllArticles() {
        return catalogService.getAllArticles();
    }

    @GetMapping("/articles/page")
    public ArticleCatalogResponseDTO listArticlesByPage(
        @RequestParam(name = "page") int pageNumber
    ) {
        return catalogService.getArticlePage(pageNumber);
    }

    @GetMapping("/articles/search")
    public ArticleCatalogResponseDTO searchArticles(
        @RequestParam(name = "query") String query
    ) {
        return catalogService.searchArticle(query);
    }
}
