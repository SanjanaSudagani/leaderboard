package com.demo.leaderboard.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LeaderboardAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;
}
