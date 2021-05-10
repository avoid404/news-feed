package com.example.news.newsfeed.service.impl;

import java.util.List;

import com.example.news.newsfeed.model.Article;
import com.example.news.newsfeed.model.ArticleCatalogResponseDTO;
import com.example.news.newsfeed.repository.ArticleRepository;
import com.example.news.newsfeed.service.IArticleCatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

@Service
public class ArticleCatalogService implements IArticleCatalogService {
    @Override
    public ArticleCatalogResponseDTO searchArticle(String query) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(query);
        List<Article> result = articleRepository.findAllBy(criteria);
        // System.out.println(articleRepository.findAll(criteria));

        ArticleCatalogResponseDTO articleCatalogResponseDTO = new ArticleCatalogResponseDTO();
        articleCatalogResponseDTO.setArticles(result);
        articleCatalogResponseDTO.setNextPage(-1);
        articleCatalogResponseDTO.setResults(result.size());
        return articleCatalogResponseDTO;
    }

    @Autowired
    private ArticleRepository articleRepository;
    
    @Value("${newsfeed.article.pageSize}")
    private int pageSize = 10;

    @Override
    public ArticleCatalogResponseDTO getAllArticles() {
        List<Article> articles = this.articleRepository.findAll(); 
        ArticleCatalogResponseDTO articleCatalogResponseDTO = new ArticleCatalogResponseDTO();
        articleCatalogResponseDTO.setArticles(articles);
        return articleCatalogResponseDTO;
    }

    @Override
    public ArticleCatalogResponseDTO getArticlePage(int pageNumber) {
        if(pageNumber <= -1) {
            // throw new Exception("Invalid Page Number: " + pageNumber);
        }

        Page<Article> page = this.articleRepository.findAll(PageRequest.of(pageNumber - 1, pageSize)); 
        List<Article> articles = page.getContent();
        int nextPage = page.hasNext() ? pageNumber + 1 : -1; 

        ArticleCatalogResponseDTO articleCatalogResponseDTO = new ArticleCatalogResponseDTO();
        articleCatalogResponseDTO.setArticles(articles);
        articleCatalogResponseDTO.setNextPage(nextPage);
        articleCatalogResponseDTO.setResults(page.getNumberOfElements());
        return articleCatalogResponseDTO;
    }
    
}
