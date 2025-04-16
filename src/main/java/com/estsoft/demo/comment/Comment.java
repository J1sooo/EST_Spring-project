package com.estsoft.demo.comment;

import com.estsoft.demo.blog.domain.Article;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    private String body;

    @CreatedDate
    private LocalDateTime createdAt;

    public Comment(String body, Article article) {
        this.body = body;
        this.article = article;
    }

    public Comment updateBody(String body) {
        this.body = body;
        return this;
    }
}
