package com.estsoft.demo.controller;

import com.estsoft.demo.domain.Member;
import com.estsoft.demo.domain.Team;
import com.estsoft.demo.dto.MemberRequest;
import com.estsoft.demo.repository.MemberRepository;
import com.estsoft.demo.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
        jdbcTemplate.execute("ALTER TABLE member AUTO_INCREMENT = 1");
    }

    @DisplayName("member 조회 검증")
    @Test
    void showMembers() throws Exception {
        Team team = teamRepository.findById(1L)
                .orElseThrow(()-> new EntityNotFoundException("Team not found"));
        Member member1 = new Member("name1", team);
        memberRepository.save(member1);

        ResultActions resultActions = mockMvc.perform(get("/members"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("name1"));
    }

    @Test
    void showOneMembers() throws Exception {
        Team team = teamRepository.findById(1L)
                .orElseThrow(()-> new EntityNotFoundException("Team not found"));
        Member member1 = new Member("메시", team);
        memberRepository.save(member1);

        ResultActions resultActions = mockMvc.perform(get("/members/{id}", member1.getId()));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(member1.getId()))
                .andExpect(jsonPath("$.name").value("메시"));
    }

    @Test
    void showPostMember() throws Exception {
        Team team = teamRepository.findById(1L)
                .orElseThrow(()-> new EntityNotFoundException("Team not found"));
        MemberRequest member = new MemberRequest("메시", team);
        Member m1 = member.toEntity();

        String json = objectMapper.writeValueAsString(m1);

        ResultActions resultActions = mockMvc.perform(post("/members")
                .contentType("application/json")
                .content(json));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(member.getTeam().getId()))
                .andExpect(jsonPath("$.name").value("메시"));

    }

    @Test
    public void saveMember() throws Exception {
        String content = """
                {
                  "name": "메시",
                  "team": {
                    "id": 4,
                    "name": "FC바르셀로나"
                  }
                }
                """;

        ResultActions resultActions = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("메시"))
                .andExpect(jsonPath("$.teamDTO.teamId").value(4))
                .andExpect(jsonPath("$.teamDTO.name").value("FC바르셀로나"));
    }
}