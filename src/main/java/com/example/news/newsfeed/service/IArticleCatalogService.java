package com.example.news.newsfeed.service;

import com.example.news.newsfeed.model.ArticleCatalogResponseDTO;

public interface IArticleCatalogService {
    ArticleCatalogResponseDTO getAllArticles();
    ArticleCatalogResponseDTO getArticlePage(int pageNumber);
    ArticleCatalogResponseDTO searchArticle(String query);
}
