package com.estsoft.demo;

import com.estsoft.demo.blog.BlogController;
import com.estsoft.demo.blog.BlogService;
import com.estsoft.demo.blog.domain.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BlogController2Test {
    @InjectMocks
    private BlogController blogController;
    @Mock
    private BlogService blogService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
    }

    @Test
    public void testSaveArticles() throws Exception {
        // given:
        String jsonContent = """
                {
                  "title": "제목",
                  "content": "내용"
                }
                """;
        Mockito.when(blogService.save(any()))
                .thenReturn(new Article("제목", "내용"));

        // when:
        ResultActions resultActions = mockMvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent));

        // then:
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").value("내용"));
    }

    // GET /api/articles 전체 블로그 글 조회 테스트
    @Test
    public void testGetAllArticles() throws Exception {
        // given
        List<Article> articleList = List.of(
                new Article("제목1", "내용1"),
                new Article("제목2", "내용2"));
        Mockito.when(blogService.findAll()).thenReturn(articleList);

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/articles")
                        .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("제목1"))
                .andExpect(jsonPath("$[0].content").value("내용1"))
                .andExpect(jsonPath("$[1].title").value("제목2"))
                .andExpect(jsonPath("$[1].content").value("내용2"));
    }

    // GET /api/articles/{id} 블로그 글 단건 조회 테스트
    @Test
    public void testGetArticleById() throws Exception {
        // given
        Article article = new Article("제목", "내용");
        Mockito.when(blogService.findById(1L)).thenReturn(article);

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/articles/1")
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").value("내용"));
    }
}