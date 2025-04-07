package com.estsoft.demo.service;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.MemberRepository;
import com.estsoft.demo.repository.Team;
import com.estsoft.demo.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public List<Team> getTeamAll() {
        return teamRepository.findAll();
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public int updateTeam(Long id, Team team) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()) {
            team.setId(id);
            teamRepository.save(team);
            return 1;
        } else {
            return 0;
        }
    }

    public int addMemberName(Long id, Member member) {
        List<Member> list = memberRepository.findByTeamId(id);
        if (!list.isEmpty()) {
            for (Member m : list) {
                m.setName(member.getName());
            }
            memberRepository.saveAll(list);
            return list.size();
        } else {
            return 0;
        }
    }
}
