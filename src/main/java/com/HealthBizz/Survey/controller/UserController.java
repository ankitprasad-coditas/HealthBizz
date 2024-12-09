package com.HealthBizz.Survey.controller;

import com.HealthBizz.Survey.dto.ApiResponseDto;
import com.HealthBizz.Survey.dto.AuthRequestDto;
import com.HealthBizz.Survey.dto.TokenResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<TokenResponseDto>> userLogin(@RequestBody AuthRequestDto authRequestDto){

    }

}
