package com.demo.leaderboard.mapper;

import com.demo.leaderboard.dto.PlayerDto;
import com.demo.leaderboard.entity.Player;

public class PlayerMapper {

    public static PlayerDto mapToPlayerDto(Player player){
      PlayerDto playerDto = new PlayerDto();
      playerDto.setId(player.getId());
      playerDto.setPlayerName(player.getPlayerName());
      if (player.getTeam() != null) {
          playerDto.setTeamId(player.getTeam().getId());
      }
      return playerDto;
    }
    public static Player mapToPlayer(PlayerDto playerDto){
        Player player= new Player();
      player.setId(playerDto.getId());
      player.setPlayerName(playerDto.getPlayerName());

      return player;
    }

}
