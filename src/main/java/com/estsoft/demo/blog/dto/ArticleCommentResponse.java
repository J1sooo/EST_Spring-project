package com.estsoft.demo.blog.dto;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ArticleCommentResponse {
    private long articleId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentNotExistArticle> comments;

    public ArticleCommentResponse(Article article) {
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();

        List<Comment> commentList = article.getComments();
        this.comments = commentList.stream()
                .map(CommentNotExistArticle::new)
                .toList();
    }
}
