package com.HealthBizz.Survey.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;

    private String role;
    private String name;
}
