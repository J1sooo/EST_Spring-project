package com.estsoft.demo.blog;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.domain.BlogRepository;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        blogRepository.deleteAll();
    }

    @Test
    void saveArticle() throws Exception {
        // given
        AddArticleRequest request = new AddArticleRequest("제목", "내용");
        String requestBody = objectMapper.writeValueAsString(request);
//        System.out.println(requestBody);
//
        // when
        ResultActions resultActions = mvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()));
    }

    @Test
    public void findAllArticles() throws Exception {
        // given
        Article savedArticle = Article.builder()
                .title("저장하는 제목")
                .content("저장하는 내용")
                .build();
        blogRepository.save(savedArticle);

        // when
        ResultActions resultActions = mvc.perform(get("/api/articles"));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(savedArticle.getTitle()))
                .andExpect(jsonPath("$[0].content").value(savedArticle.getContent()));
    }

    @Test
    public void findByIdArticles() throws Exception {
        // given
        Article savedArticle = Article.builder()
                .title("저장하는 제목")
                .content("저장하는 내용")
                .build();
        blogRepository.save(savedArticle);

        // when
        ResultActions resultActions = mvc.perform(get("/api/articles/{id}", savedArticle.getId()));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(savedArticle.getTitle()))
                .andExpect(jsonPath("$.content").value(savedArticle.getContent()));
    }

    @Test
    public void deleteArtricle() throws Exception {
        // given
        Article article = blogRepository.save(new Article("제목","내용"));

        // when
        ResultActions resultActions = mvc.perform(delete("/api/articles/{id}", article.getId()));

        // then
        resultActions.andExpect(status().isOk());

        List<Article> list = blogRepository.findAll();
        Assertions.assertThat(list).isEmpty();
        Assertions.assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void deleteAllArticle() throws Exception {
        // given
        List<Article> articles = IntStream.range(0, 3)
                .mapToObj(i -> Article.builder()
                        .title("제목")
                        .content("내용")
                        .build())
                        .toList();
        blogRepository.saveAll(articles);

        // when
        ResultActions resultActions = mvc.perform(delete("/api/articles"));

        // then
        resultActions.andExpect(status().isOk());
        List<Article> list = blogRepository.findAll();
        Assertions.assertThat(list).isEmpty();
    }

    @Test
    public void updateArticle() throws Exception {
        // given
        Article article = blogRepository.save(new Article("제목", "내용"));

        UpdateArticleRequest updateArticle = new UpdateArticleRequest("제목111","내용1111");

        // when
        ResultActions resultActions = mvc.perform(put("/api/articles/{id}",article.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(updateArticle)));


        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(updateArticle.getTitle()))
                .andExpect(jsonPath("$.content").value(updateArticle.getContent()));
    }

    @Test
    public void updateArticleFailed() throws Exception {
        // given
        UpdateArticleRequest updateArticle = new UpdateArticleRequest("제목111","내용1111");

        // when
        ResultActions resultActions = mvc.perform(put("/api/articles/{id}", 111L)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(updateArticle)));

        // then
        resultActions.andExpect(status().isNotFound());
    }
}