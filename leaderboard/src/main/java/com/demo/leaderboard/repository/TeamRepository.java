package com.demo.leaderboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.leaderboard.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{

}
