package com.estsoft.demo.controller;

import com.estsoft.demo.domain.Member;
import com.estsoft.demo.dto.MemberDTO;
import com.estsoft.demo.dto.MemberRequest;
import com.estsoft.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/members")
    public List<MemberDTO> showMembers() {
        List<Member> members = memberService.getMemberAll();
        return members.stream().map(MemberDTO::new).toList();
    }

    @ResponseBody
    @PostMapping("/members")
    public MemberDTO saveMember(@RequestBody MemberRequest request) {
        Member member = memberService.saveMember(request.toEntity());
        return new MemberDTO(member);
    }

    @ResponseBody
    @GetMapping("/members/{id}")
    public MemberDTO findMember(@PathVariable Long id) {
        Member member = memberService.findMember(id);
        return new MemberDTO(member);
    }

    @ResponseBody
    @DeleteMapping("/members/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        try {
            memberService.deleteMember(id);
            return ResponseEntity.ok("삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("삭제 실패");
        }
    }

    @ResponseBody
    @GetMapping("/search/members")
    public List<MemberDTO> selectMembersByName(@RequestParam String name) {
        List<Member> member = memberService.selectMemberByName(name);
        return member.stream().map(MemberDTO::new).toList();
    }
}
