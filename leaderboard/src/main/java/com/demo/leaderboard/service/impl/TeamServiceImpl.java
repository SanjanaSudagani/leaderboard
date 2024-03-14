package com.demo.leaderboard.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.leaderboard.dto.TeamDto;
import com.demo.leaderboard.entity.Team;
import com.demo.leaderboard.exceptions.ResourceNotFoundException;
import com.demo.leaderboard.mapper.TeamMapper;
import com.demo.leaderboard.repository.TeamRepository;
import com.demo.leaderboard.service.TeamService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService{

    @Autowired
    public TeamRepository teamRepository;

    @Override
    public TeamDto createTeam(TeamDto teamDto) {
        Team team=TeamMapper.mapToTeam(teamDto);
        Team savedTeam=teamRepository.save(team);

        return TeamMapper.mapToTeamDto(savedTeam);
    }

    @Override
    public TeamDto getTeamById(Long teamId) {
        Team team=teamRepository.findById(teamId)
        .orElseThrow(()-> new ResourceNotFoundException("Team does not exist with give id: "+teamId));

        return TeamMapper.mapToTeamDto(team);
       
    }

    @Override
    public List<TeamDto> getAllTeams() {
        List<Team> teams=teamRepository.findAll();
        return teams.stream().map((team)-> TeamMapper.mapToTeamDto(team))
        .collect(Collectors.toList());
    }

     @Override
    public TeamDto updateTeam(Long teamId, TeamDto updatedTeam) {
        Team team=teamRepository.findById(teamId)
        .orElseThrow(()-> new ResourceNotFoundException("Team does not exist with give id: "+teamId));

        team.setTeamName(updatedTeam.getTeamName());
        team.setTotalMatches(updatedTeam.getTotalMatches());
        team.setMatchesWon(updatedTeam.getMatchesWon());
        team.setMatchesLost(updatedTeam.getMatchesLost());
        team.setScore(updatedTeam.getScore());

        Team updatedTeamObj= teamRepository.save(team);

        return TeamMapper.mapToTeamDto(updatedTeamObj);
    }

    @Override
    public void deleteTeam(Long teamId) {
        Team team=teamRepository.findById(teamId)
        .orElseThrow(()-> new ResourceNotFoundException("Team does not exist with give id: "+teamId));

        teamRepository.deleteById(teamId);
    }
}
