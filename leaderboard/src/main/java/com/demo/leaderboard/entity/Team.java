package com.demo.leaderboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="team")
public class Team {
   
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="team_name")
	private String teamName;
	
	@Column(name="total_matches")
	private Long totalMatches;
	
	@Column(name="matches_won")
	private Long matchesWon;

    @Column(name="matches_lost")
	private Long matchesLost;

    @Column(name="score")
	private Long score;
    
}
