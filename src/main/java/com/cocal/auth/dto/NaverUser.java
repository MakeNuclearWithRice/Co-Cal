package com.cocal.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NaverUser {

    private String resultCode;
    private String message;
    private UserInfo response;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfo{

        private String id;
        private String nickName;
        private String name;
        private String email;
        private String gender;
        private String age;
        private String birthday;
        private String profileImage;
        private String birthYear;
        private String mobile;
    }

}
