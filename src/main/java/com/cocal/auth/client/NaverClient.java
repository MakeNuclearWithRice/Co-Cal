package com.cocal.auth.client;

import com.cocal.auth.dto.naver.NaverAuthCodeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverClient {

    @Value("${spring.security.oauth2.client.provider.naver.authorization-uri}")
    private String NaverAuthUri;

    @Value("${spring.security.oauth2.client.provider.naver.token-uri}")
    private String NaverTokenUri;

    public String generateUrl(){
        var naverAuthCodeRequest = new NaverAuthCodeRequest();

        return UriComponentsBuilder.fromUriString(NaverAuthUri)
                .queryParams(naverAuthCodeRequest.toMultiValueMap())
                .build()
                .encode()
                .toUriString();

    }

}
