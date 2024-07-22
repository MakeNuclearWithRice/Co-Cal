package com.cocal.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoUser {

    @JsonProperty("kakao_account")
    private UserInfo kakaoAccount;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming
    public static class UserInfo{
        private String name;
        private String email;
    }
}

