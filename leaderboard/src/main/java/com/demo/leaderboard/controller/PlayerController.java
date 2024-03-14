package com.demo.leaderboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.leaderboard.dto.PlayerDto;
import com.demo.leaderboard.service.PlayerService;

import lombok.AllArgsConstructor;


@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto playerDto){
        PlayerDto savedPlayer=playerService.createPlayer(playerDto);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable("id") Long playerId){
        PlayerDto playerDto=playerService.getPlayerById(playerId);
        return ResponseEntity.ok(playerDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<PlayerDto>> getAllPlayers(){
        List<PlayerDto> players=playerService.getAllPlayers();
        return ResponseEntity.ok(players);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable("id") Long playerId){
        playerService.deletePlayer(playerId);
        return ResponseEntity.ok("Delete Successful");
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/team")
    public ResponseEntity<List<PlayerDto>> getPlayersByTeamId( @RequestParam("teamId") Long teamId) {
        List<PlayerDto> players = playerService.getPlayersByTeamId(teamId);
        return ResponseEntity.ok(players);
    }
}
