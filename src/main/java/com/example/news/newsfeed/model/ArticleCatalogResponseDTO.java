package com.example.news.newsfeed.model;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class ArticleCatalogResponseDTO {
    private int nextPage = -1;
    private int results;
    private List<Article> articles;
}
