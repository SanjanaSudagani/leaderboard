package com.demo.leaderboard.security;

import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    private static final Logger logger = Logger.getLogger(JwtTokenProvider.class.getName());

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    public String generateToken(Authentication authentication){
        String username=authentication.getName();

        Date currentDate= new Date();

        Date expireDate=new Date(currentDate.getTime()+jwtExpirationDate);

        String token=Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(expireDate)
        .signWith(key())
        .compact();

//         System.out.println("Current Date: " + currentDate);
// System.out.println("JWT Expiration Date: " + jwtExpirationDate);

        return token;
    }

    private Key key(){
        
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token){
        Claims claims=Jwts.parser()
        .setSigningKey(key())
        .build()
        .parseClaimsJws(token)
        .getBody();

        String username=claims.getSubject();

        return username;
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser()
                .setSigningKey(key())
                .build()
                .parse(token);
            return true;
        } catch (JwtException e) {
            // Log the exception or any relevant information
            logger.severe("Failed to validate JWT token: " + e.getMessage());
            return false;
        }
    }
}
