package com.estsoft.demo.blog.dto;

import com.estsoft.demo.blog.domain.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public ArticleDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
    }
}