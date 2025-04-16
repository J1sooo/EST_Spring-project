package com.estsoft.demo.comment.dto;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.ArticleResponse;
import com.estsoft.demo.comment.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CommentResponse {
    private Long commentId;
    private Long articleId;
    private String body;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private ArticleResponse article;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getCommentId();
        this.body = comment.getBody();
        this.createdAt = comment.getCreatedAt();

        Article articleEntity = comment.getArticle();
        this.articleId = articleEntity.getId();
        this.article = new ArticleResponse(articleEntity);
    }
}
