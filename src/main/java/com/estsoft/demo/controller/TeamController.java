package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.Team;
import com.estsoft.demo.repository.TeamDTO;
import com.estsoft.demo.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @ResponseBody
    @GetMapping("/teams")
    public List<TeamDTO> showMembers() {
        List<Team> teams = teamService.getTeamAll();
        return teams.stream().map(TeamDTO::new).toList();
    }

    @ResponseBody
    @PostMapping("/teams")
    public Team saveTeam(@RequestBody Team team) {
        return teamService.saveTeam(team);
    }

    @ResponseBody
    @PutMapping("/teams/{id}")
    public int putTeam(@PathVariable Long id, @RequestBody Team team) {
        return teamService.updateTeam(id, team);
    }

    @ResponseBody
    @PutMapping("/teams/{id}/members")
    public int addMemberName(@PathVariable Long id, @RequestBody Member member) {
        return teamService.addMemberName(id, member);
    }
}
