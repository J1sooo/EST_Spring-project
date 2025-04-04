//package com.estsoft.demo.controller;
//
//import com.estsoft.demo.repository.Member;
//import com.estsoft.demo.repository.MemberRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class MemberControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private WebApplicationContext context;
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @BeforeEach
//    public void mockMvcSetUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @AfterEach
//    public void cleanUp() {
//        memberRepository.deleteAll();
//    }
//
//    @DisplayName("member 조회 검증")
//    @Test
//    void showMembers() throws Exception {
//        Member member = new Member(1L,"name1");
//        memberRepository.save(member);
//
//        ResultActions resultActions = mockMvc.perform(get("/members"));
//
//        resultActions.andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(member.getId()))
//                .andExpect(jsonPath("$[0].name").value(member.getName()));
//    }
//}