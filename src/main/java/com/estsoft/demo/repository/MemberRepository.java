package com.estsoft.demo.repository;

import com.estsoft.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNameContaining(String name);
    List<Member> findByTeamId(Long teamId);
}
