package com.example.news.newsfeed.model;

// import java.util.Date;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Article {
    
    @Id
    private String id;

    private String author;
    @TextIndexed(weight = 2)
    private String title;
    private String description;

    @Indexed(unique = true)
    private String url;
    private String urlToImage;
    private LocalDateTime publishedAt;
    
    @TextIndexed(weight = 1)
    private String content;
    @Override
    public String toString() {
        return "Article [url=" + url + ", author=" + author + ", id=" + id + ", publishedAt="
                + publishedAt + ", title=" + title + "]";
    }
}
