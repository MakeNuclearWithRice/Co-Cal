package com.cocal.auth.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class NaverClientProperties {

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String clientSecret;

    private final String grantType = "authorization_code";
    private final String state = "STATE_STRING";
    private final String responseType = "code";
    private final String redirectUri = "http://localhost:8080/auth/naver/login";


    public MultiValueMap<String, String> getNaverCodeParam() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("response_type", responseType);
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("state", state);
        return params;
    }

    public MultiValueMap<String, String> getNaverTokenParam(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);
        params.add("state", state);

        return params;
    }

}
