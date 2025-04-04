package com.estsoft.demo.repository;


import lombok.Getter;

@Getter
public class MemberDTO {
    private Long id;
    private String name;
    private TeamDTO teamDTO;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.teamDTO = new TeamDTO(member.getTeam());
    }
}
