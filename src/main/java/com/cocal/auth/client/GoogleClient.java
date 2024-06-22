package com.cocal.auth.client;

import com.cocal.auth.dto.google.GoogleClientProperties;
import com.cocal.auth.dto.google.GoogleTokenResponse;
import com.cocal.auth.dto.User;
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
public class GoogleClient{

    @Value("${spring.security.oauth2.client.provider.google.authorization-uri}")
    private String googleAuthUri;

    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String googleTokenUri;

    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    private String googleEmailUri;

    private final GoogleClientProperties googleClientProperties;


    public String generateUrl() {
        return UriComponentsBuilder.fromUriString(googleAuthUri)
                .queryParams(googleClientProperties.getGoogleCodeParam())
                .encode()
                .build()
                .toUriString();
    }


    public String getToken(String code) {
        var uri = UriComponentsBuilder.fromUriString(googleTokenUri)
                .encode()
                .build()
                .toUri();
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var httpEntity = new HttpEntity<>(googleClientProperties.getGoogleTokenParam(code), headers);
        var responseType = new ParameterizedTypeReference<GoogleTokenResponse>() {};
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.POST,
                httpEntity,
                responseType);
        var googleTokenResponse = responseEntity.getBody();

        return googleTokenResponse.getAccessToken();
    }
    public User getUserInfo(String accessToken){
        var uri = UriComponentsBuilder.fromUriString(googleEmailUri)
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

        return responseEntity.getBody();
    }


}
