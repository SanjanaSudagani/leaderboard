package com.demo.leaderboard.service;

import com.demo.leaderboard.dto.JwtAuthResponse;
import com.demo.leaderboard.dto.LoginDto;
import com.demo.leaderboard.dto.RegisterDto;

public interface AuthService {
String register(RegisterDto registerDto);

JwtAuthResponse login(LoginDto loginDto);
    
} 
