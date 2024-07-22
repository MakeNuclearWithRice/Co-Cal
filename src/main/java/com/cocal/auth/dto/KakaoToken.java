package com.cocal.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoToken {

    private String tokenType;
    private String accessToken;
    private Integer expiresIn;
    private String refreshToken;
    private Integer refreshTokenExpiresIn;
}
