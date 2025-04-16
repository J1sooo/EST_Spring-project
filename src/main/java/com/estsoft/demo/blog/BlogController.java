package com.estsoft.demo.blog;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.ArticleCommentResponse;
import com.estsoft.demo.blog.dto.ArticleResponse;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> saveArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedArticle.toDto());
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        List<Article> articles = blogService.findAll();

        List<ArticleResponse> responses = articles.stream().map(ArticleResponse::new).toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
        Article articles = blogService.findById(id);
        ArticleResponse articleResponse = new ArticleResponse(articles);
        return ResponseEntity.ok(articleResponse);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticles(@PathVariable Long id) {
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/articles")
    public ResponseEntity<Void> deleteAllArticles() {
        blogService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long id,
                                                         @RequestBody UpdateArticleRequest request) {
        Article article = blogService.updateArticle(id, request);
        ArticleResponse articleResponse = article.toDto();
        return ResponseEntity.ok(articleResponse);
    }

    // IllegalArgumentException 500x -> 4xx
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String HandlerIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }

    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<ArticleCommentResponse> findArticleWithComment(@PathVariable Long articleId) {
        Article article = blogService.findById(articleId);

        return ResponseEntity.ok(new ArticleCommentResponse(article));
    }
}
