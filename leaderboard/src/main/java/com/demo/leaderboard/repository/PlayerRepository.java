package com.demo.leaderboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.leaderboard.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{

    List<Player> findByTeamId(Long teamId);

}
