package com.cocal.auth.service;

public enum OAuth {

    GOOGLE,
    KAKAO,
    NAVER;


    public static OAuth from(String platform) {
        return OAuth.valueOf(platform.toUpperCase());
    }


}
