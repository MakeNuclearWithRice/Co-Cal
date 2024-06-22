package com.cocal.auth.dto.google;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@Component
public class GoogleClientProperties {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    private final String redirectUri = "http://localhost:8080/auth/login";
    private final String grantType = "authorization_code";

    private final String scope = "https://www.googleapis.com/auth/userinfo.email";
    private final String accessType = "offline";
    private final String includeGrantedScopes = "true";
    private final String responseType = "code";

    public MultiValueMap<String,String> getGoogleCodeParam() {
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("scope", scope);
        map.add("access_type", accessType);
        map.add("include_granted_scopes", includeGrantedScopes);
        map.add("response_type", responseType);
        map.add("redirect_uri", redirectUri);
        map.add("client_id", clientId);
        return map;
    }

    public MultiValueMap<String,String> getGoogleTokenParam(String code) {
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("code", code);
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("redirect_uri", redirectUri);
        map.add("grant_type", grantType);
        return map;
    }
}

