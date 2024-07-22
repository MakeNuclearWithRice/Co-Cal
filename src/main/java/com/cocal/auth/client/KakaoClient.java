package com.cocal.auth.client;

import com.cocal.auth.dto.*;
import com.cocal.auth.properties.KakaoClientProperties;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoClient implements AuthClientIfs {

    @Value("${spring.security.oauth2.client.provider.kakao.authorization-uri}")
    private String kakaoAuthUri;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String kakaoTokenUri;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String kakaoUserInfoUri;

    private final KakaoClientProperties kakaoClientProperties;


    @Override
    public String generateUrl(){
        return UriComponentsBuilder.fromUriString(kakaoAuthUri)
                .queryParams(kakaoClientProperties.getKakaoCodeParam())
                .encode()
                .build()
                .toUriString();
    }

    @Override
    public String getToken(String code) {
        var uri = UriComponentsBuilder.fromUriString(kakaoTokenUri)
                .encode()
                .build()
                .toUri();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var httpEntity = new HttpEntity<>(kakaoClientProperties.getKakaoTokenParam(code), headers);
        var responseType = new ParameterizedTypeReference<KakaoToken>() {};
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.POST,
                httpEntity,
                responseType);
        var kakaoToken = responseEntity.getBody();

        return kakaoToken.getAccessToken();
    }

    @Override
    public User getUserInfo(String accessToken) {

        String[] propertyKeys = new String[]{"kakao_account.name", "kakao_account.email"};

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("property_keys", "[\"kakao_account.name\",\"kakao_account.email\"]");


        var uri = UriComponentsBuilder.fromUriString(kakaoUserInfoUri)
        //        .queryParams(multiValueMap)
                .encode()
                .build()
                .toUri();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);
        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<KakaoUser>() {};
        var responseEntity = new RestTemplate().exchange(uri,
                HttpMethod.POST,
                httpEntity,
                responseType);

        var user = new User();
        user.setName(responseEntity.getBody().getKakaoAccount().getName());
        user.setEmail(responseEntity.getBody().getKakaoAccount().getEmail());

        log.info("I am Kakao");
        return user;

    }

    @Override
    public OAuth getPlatform() {
        return OAuth.KAKAO;
    }
}
