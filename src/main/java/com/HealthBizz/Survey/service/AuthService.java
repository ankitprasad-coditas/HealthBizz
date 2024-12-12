package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.dto.AuthRequestDto;
import com.HealthBizz.Survey.dto.TokenResponseDto;
import com.HealthBizz.Survey.entity.User;
import com.HealthBizz.Survey.exception.UnauthorisedException;
import com.HealthBizz.Survey.reporsitory.UserRepo;
import com.HealthBizz.Survey.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;

    @Autowired
    public AuthService(JwtService jwtService, AuthenticationManager authenticationManager, UserRepo userRepo) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
    }

    public TokenResponseDto login(AuthRequestDto authRequestDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmailId(),authRequestDto.getPassword()));
        if(authentication.isAuthenticated()){
            String accessToken = jwtService.generateAccessToken(authRequestDto.getEmailId());
            String refreshToken = jwtService.generateRefreshToken(authRequestDto.getEmailId());
            User user = userRepo.findByEmailId(authRequestDto.getEmailId()).get();
            String name = user.getName();
            String role = String.valueOf(user.getRole().getName());
            TokenResponseDto tokenResponseDto = TokenResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .name(name)
                    .role(role)
                    .build();
            return tokenResponseDto;
        }
        else{
            throw new UnauthorisedException("Invalid Login Details");
        }
    }
}
