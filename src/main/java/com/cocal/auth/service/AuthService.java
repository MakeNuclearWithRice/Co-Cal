package com.cocal.auth.service;

import com.cocal.auth.client.AuthClientIfs;
import com.cocal.auth.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final OAuthStrategy oAuthStrategy;



    public String generateUrl(String platform){
        AuthClientIfs authClientIfs = oAuthStrategy.getClient(OAuth.from(platform));
        return authClientIfs.generateUrl();
    };

    public String getToken(String platform, String code){
        AuthClientIfs authClientIfs = oAuthStrategy.getClient(OAuth.from(platform));
        return authClientIfs.getToken(code);
    };

    public User getUserInfo(String platform, String accessToken){
        AuthClientIfs authClientIfs = oAuthStrategy.getClient(OAuth.from(platform));
        return authClientIfs.getUserInfo(accessToken);
    };
}
