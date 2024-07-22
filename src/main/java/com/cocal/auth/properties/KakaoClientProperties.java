package com.cocal.auth.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class KakaoClientProperties {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;

    private final String redirectUri = "http://localhost:8080/auth/kakao/login";
    private final String responseType = "code";

    private final String grantType = "authorization_code";



    public MultiValueMap<String, String> getKakaoCodeParam(){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("response_type", responseType);
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        return params;
    }

    public MultiValueMap<String, String> getKakaoTokenParam(String code){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("client_secret", clientSecret);
        return params;
    }

    public MultiValueMap<String, String> getKakaoUserInfoParam(){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("property_keys", "kakao_account.name");
        params.add("property_keys", "kakao_account.email");
        return params;
    }


}
