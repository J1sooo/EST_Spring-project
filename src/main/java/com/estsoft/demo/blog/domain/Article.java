package com.estsoft.demo.blog.domain;

import com.estsoft.demo.blog.dto.ArticleResponse;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse toDto() {
        return new ArticleResponse(id,title,content);
    }

//    public Article(Builder builder) {
//        this.id = builder.id;
//        this.title = builder.title;
//        this.content = builder.content;
//    }
//
//    private static class Builder {
//        private Long id;
//        private String title;
//        private String content;
//
//        Builder title(String title) {
//            this.title = title;
//            return this;
//        }
//
//        Builder content(String content) {
//            this.content = content;
//            return this;
//        }
//        Article build() {
//            return new Article(this);
//        }
//    }
}