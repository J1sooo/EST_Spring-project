package com.estsoft.demo.service;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> getMemberAll() {
        return memberRepository.findAll();
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Member findMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.orElse(null);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public List<Member> selectMemberByName(String name) {
        return memberRepository.findByNameContaining(name);
    }
}
