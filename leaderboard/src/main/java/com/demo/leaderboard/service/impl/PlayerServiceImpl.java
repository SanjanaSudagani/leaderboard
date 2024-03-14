package com.demo.leaderboard.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.leaderboard.dto.PlayerDto;
import com.demo.leaderboard.entity.Player;
import com.demo.leaderboard.entity.Team;
import com.demo.leaderboard.exceptions.ResourceNotFoundException;
import com.demo.leaderboard.mapper.PlayerMapper;
import com.demo.leaderboard.repository.PlayerRepository;
import com.demo.leaderboard.repository.TeamRepository;
import com.demo.leaderboard.service.PlayerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public PlayerDto createPlayer(PlayerDto playerDto) {
        Player player=PlayerMapper.mapToPlayer(playerDto);

        Team team= teamRepository.findById(playerDto.getTeamId())
        .orElseThrow(()-> new ResourceNotFoundException("Team does not exist with give id: "+ playerDto.getTeamId()));

        player.setTeam(team);

        Player savedPlayer=playerRepository.save(player);

        return PlayerMapper.mapToPlayerDto(savedPlayer);
    }

    @Override
    public PlayerDto getPlayerById(Long playerId) {
        Player player=playerRepository.findById(playerId)
        .orElseThrow(()-> new ResourceNotFoundException("Player does not exist with give id: "+playerId));

        return PlayerMapper.mapToPlayerDto(player);
       
    }


    @Override
    public List<PlayerDto> getAllPlayers() {
        List<Player> players=playerRepository.findAll();
        return players.stream().map((player)-> PlayerMapper.mapToPlayerDto(player))
        .collect(Collectors.toList());
    }


    @Override
    public void deletePlayer(Long playerId) {
        Player player=playerRepository.findById(playerId)
        .orElseThrow(()-> new ResourceNotFoundException("Player does not exist with give id: "+playerId));

        playerRepository.deleteById(playerId);
    }


    @Override
public List<PlayerDto> getPlayersByTeamId(Long teamId) {
    List<Player> players = playerRepository.findByTeamId(teamId);
    return players.stream()
                  .map(PlayerMapper::mapToPlayerDto)
                  .collect(Collectors.toList());
}
}
