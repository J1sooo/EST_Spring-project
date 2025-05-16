package com.estsoft.demo.dto;


import com.estsoft.demo.domain.Member;
import com.estsoft.demo.domain.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MemberRequest {
    private String name;
    private TeamRequest team;

    public MemberRequest(String name, Team team) {
        this.name = name;
        this.team = new TeamRequest(team);
    }

    public Member toEntity() {
        return new Member(name, team.toEntity());
    }

    @Getter
    @NoArgsConstructor
    public static class TeamRequest {
        private Long id;
        private String name;

        public TeamRequest(Team team) {
            this.id = team.getId();
            this.name = team.getName();
        }

        // DTO -> Entity
        public Team toEntity() {
            return new Team(id, name);
        }
    }
}
