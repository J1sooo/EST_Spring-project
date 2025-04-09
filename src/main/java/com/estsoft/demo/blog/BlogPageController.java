package com.estsoft.demo.blog;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.ArticleViewResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogPageController {
    private final BlogService blogService;

    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/articles")
    public String articles(Model model) {
        List<ArticleViewResponse> articleList = blogService.findAll()
                .stream().map(ArticleViewResponse::new)
                .toList();

        model.addAttribute("articles", articleList);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);

        model.addAttribute("article", new ArticleViewResponse(article));
        return "article";
    }

    @GetMapping("/new-article")
    public String showBlogEditPage(@RequestParam(required = false) Long id, Model model) {
        if (id == null) { // 생성
            model.addAttribute("article",new ArticleViewResponse());
        } else { // 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}
