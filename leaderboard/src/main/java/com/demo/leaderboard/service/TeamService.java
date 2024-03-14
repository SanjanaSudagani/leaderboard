package com.demo.leaderboard.service;

import java.util.List;

import com.demo.leaderboard.dto.TeamDto;

public interface TeamService {

    TeamDto createTeam(TeamDto teamDto);

    TeamDto getTeamById(Long teamId);

    List<TeamDto> getAllTeams();

    TeamDto updateTeam(Long teamId, TeamDto updatedTeam);

    void deleteTeam(Long teamId);

}
