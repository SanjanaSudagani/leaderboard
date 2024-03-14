package com.demo.leaderboard.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.leaderboard.dto.JwtAuthResponse;
import com.demo.leaderboard.dto.LoginDto;
import com.demo.leaderboard.dto.RegisterDto;
import com.demo.leaderboard.entity.Role;
import com.demo.leaderboard.entity.User;
import com.demo.leaderboard.exceptions.LeaderboardAPIException;
import com.demo.leaderboard.repository.RoleRepository;
import com.demo.leaderboard.repository.UserRepository;
import com.demo.leaderboard.security.JwtTokenProvider;
import com.demo.leaderboard.service.AuthService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {

            throw new LeaderboardAPIException(HttpStatus.BAD_REQUEST, "Username already exists");
            
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {

            throw new LeaderboardAPIException(HttpStatus.BAD_REQUEST, "Email already exists");
            
        }

        User user= new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));


        Set<Role> roles=new HashSet<>();
        Role userRole= roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Registration Successful";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
        
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginDto.getUsernameOrEmail(), 
            loginDto.getPassword()
            ));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token=jwtTokenProvider.generateToken(authentication);

            Optional<User> userOptional= userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail());
        
            String role=null;
            if (userOptional.isPresent()) {

                User loggedInUser=userOptional.get();
                Optional<Role> optionalRole= loggedInUser.getRoles().stream().findFirst();

                if(optionalRole.isPresent()){
                    Role userRole=optionalRole.get();
                    role=userRole.getName();
                }
                
            }

            JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
            jwtAuthResponse.setRole(role);
            jwtAuthResponse.setAccessToken(token);
        
            return jwtAuthResponse;
    }

    

}
