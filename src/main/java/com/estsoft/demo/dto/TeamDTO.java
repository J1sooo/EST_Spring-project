package com.estsoft.demo.dto;

import com.estsoft.demo.domain.Team;
import lombok.Getter;

@Getter
public class TeamDTO {
    private Long teamId;
    private String name;

    public TeamDTO(Team team) {
        this.teamId = team.getId();
        this.name = team.getName();
    }
}
