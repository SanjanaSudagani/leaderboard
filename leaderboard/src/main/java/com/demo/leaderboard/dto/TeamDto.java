package com.demo.leaderboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    private Long id;
	private String teamName;
	private long totalMatches;
	private long matchesWon;
    private long matchesLost;
    private long score;

}
