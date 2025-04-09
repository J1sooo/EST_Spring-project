package com.estsoft.demo.blog.domain;

import com.estsoft.demo.blog.dto.ArticleResponse;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Article(String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
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