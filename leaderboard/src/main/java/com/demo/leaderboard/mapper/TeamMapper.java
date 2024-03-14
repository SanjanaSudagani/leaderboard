package com.demo.leaderboard.mapper;

import com.demo.leaderboard.dto.TeamDto;
import com.demo.leaderboard.entity.Team;

public class TeamMapper {

    public static TeamDto mapToTeamDto(Team team){
        return new TeamDto(
				team.getId(),
				team.getTeamName(),
				team.getMatchesWon(),
				team.getMatchesLost(),
                team.getTotalMatches(),
                team.getScore()
				);
    }

    public static Team mapToTeam(TeamDto teamDto){
        return new Team(
				teamDto.getId(),
				teamDto.getTeamName(),
				teamDto.getMatchesWon(),
				teamDto.getMatchesLost(),
                teamDto.getTotalMatches(),
                teamDto.getScore()
				);
    }

}
