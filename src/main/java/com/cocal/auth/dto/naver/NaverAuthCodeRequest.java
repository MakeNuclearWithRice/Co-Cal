package com.cocal.auth.dto.naver;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class NaverAuthCodeRequest {
    private String responseType = "code";
    private String clientId = "pkTYjDhOmFEFoCjuRb_M";
    private String redirectUri = "http://localhost:8080/auth/login";
    private String state ="STATE_STRING";

    public MultiValueMap<String, String> toMultiValueMap() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("response_type", responseType);
        map.add("client_id", clientId);
        map.add("redirect_uri", redirectUri);
        map.add("state", state);
        return map;
    }
}
