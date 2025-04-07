package com.estsoft.demo.blog;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.domain.BlogRepository;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }
}
