package com.example.news.newsfeed.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechCrunchResponse implements Serializable {
    
    private String status;
    private Integer totalResults;
    List<Article> articles; 
}
