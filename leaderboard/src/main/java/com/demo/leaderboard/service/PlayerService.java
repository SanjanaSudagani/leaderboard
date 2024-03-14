package com.demo.leaderboard.service;

import java.util.List;

import com.demo.leaderboard.dto.PlayerDto;

public interface PlayerService {

    PlayerDto createPlayer(PlayerDto playerDto);

    PlayerDto getPlayerById(Long playerId);

    List<PlayerDto> getAllPlayers();

    void deletePlayer(Long playerId);

    List<PlayerDto> getPlayersByTeamId(Long teamId);

}
