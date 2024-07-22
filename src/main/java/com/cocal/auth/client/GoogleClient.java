package com.cocal.auth.client;

import com.cocal.auth.properties.GoogleClientProperties;
import com.cocal.auth.dto.GoogleToken;
import com.cocal.auth.dto.User;
import com.cocal.auth.service.OAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleClient implements AuthClientIfs{

    @Value("${spring.security.oauth2.client.provider.google.authorization-uri}")
    private String googleAuthUri;

    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String googleTokenUri;

    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    private String googleUserInfoUri;

    private final GoogleClientProperties googleClientProperties;


    @Override
    public String generateUrl() {
        return UriComponentsBuilder.fromUriString(googleAuthUri)
                .queryParams(googleClientProperties.getGoogleCodeParam())
                .encode()
                .build()
                .toUriString();
    }

    @Override
    public String getToken(String code) {
        var uri = UriComponentsBuilder.fromUriString(googleTokenUri)
                .encode()
                .build()
                .toUri();
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var httpEntity = new HttpEntity<>(googleClientProperties.getGoogleTokenParam(code), headers);
        var responseType = new ParameterizedTypeReference<GoogleToken>() {};
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.POST,
                httpEntity,
                responseType);
        var googleToken = responseEntity.getBody();

        return googleToken.getAccessToken();
    }

    @Override
    public User getUserInfo(String accessToken){
        var uri = UriComponentsBuilder.fromUriString(googleUserInfoUri)
                .encode()
                .build()
                .toUri();
        var headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<User>() {};
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
        log.info("I am google");


        return responseEntity.getBody();
    }

    @Override
    public OAuth getPlatform() {
        return OAuth.GOOGLE;
    }


}
