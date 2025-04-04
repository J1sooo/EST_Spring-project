package com.estsoft.demo.repository;

import lombok.Getter;

@Getter
public class TeamDTO {
    private Long id;
    private String name;

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
    }
}
