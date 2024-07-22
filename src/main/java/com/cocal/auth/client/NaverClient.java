package com.cocal.auth.client;

import com.cocal.auth.dto.NaverToken;
import com.cocal.auth.dto.NaverUser;
import com.cocal.auth.dto.User;
import com.cocal.auth.properties.NaverClientProperties;
import com.cocal.auth.service.OAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class NaverClient implements AuthClientIfs{

    @Value("${spring.security.oauth2.client.provider.naver.authorization-uri}")
    private String naverAuthUri;

    @Value("${spring.security.oauth2.client.provider.naver.token-uri}")
    private String naverTokenUri;

    @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
    private String naverUserInfoUri;

    private final NaverClientProperties naverClientProperties;


    @Override
    public String generateUrl(){

        return UriComponentsBuilder.fromUriString(naverAuthUri)
                .queryParams(naverClientProperties.getNaverCodeParam())
                .encode()
                .build()
                .toUriString();

    }

    @Override
    public String getToken(String code) {
        var uri = UriComponentsBuilder.fromUriString(naverTokenUri)
                .queryParams(naverClientProperties.getNaverTokenParam(code))
                .encode()
                .build()
                .toUri();

        var naverToken = new RestTemplate().getForObject(uri, NaverToken.class);

        return naverToken.getAccessToken();
    }

    @Override
    public User getUserInfo(String accessToken) {

        var uri = UriComponentsBuilder.fromUriString(naverUserInfoUri)
                .encode()
                .build()
                .toUri();
        var headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        var httpEntity = new HttpEntity<>(headers);

        var responseEntity = new RestTemplate().exchange(uri,
                HttpMethod.GET,
                httpEntity,
                NaverUser.class);

        User user = new User();

        user.setName(responseEntity.getBody().getResponse().getName());
        user.setEmail(responseEntity.getBody().getResponse().getEmail());

        log.info("i am naver");
        return user;
    }

    @Override
    public OAuth getPlatform() {
        return OAuth.NAVER;
    }


}
