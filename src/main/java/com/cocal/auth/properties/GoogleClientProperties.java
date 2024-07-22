package com.cocal.auth.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class GoogleClientProperties {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    private final String redirectUri = "http://localhost:8080/auth/google/login";
    private final String scope = "https://www.googleapis.com/auth/userinfo.email";
    private final String accessType = "offline";
    private final String includeGrantedScopes = "true";
    private final String responseType = "code";
    private final String grantType = "authorization_code";


    public MultiValueMap<String,String> getGoogleCodeParam() {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("scope", scope);
        params.add("access_type", accessType);
        params.add("include_granted_scopes", includeGrantedScopes);
        params.add("response_type", responseType);
        params.add("redirect_uri", redirectUri);
        params.add("client_id", clientId);
        return params;
    }

    public MultiValueMap<String,String> getGoogleTokenParam(String code) {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", grantType);
        return params;
    }
}

