package com.cocal.auth.service;


import com.cocal.auth.client.AuthClientIfs;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class OAuthStrategy {

    private final Map<OAuth, AuthClientIfs> authClientsMap;

    public OAuthStrategy(List<AuthClientIfs> authClientsList) {
        EnumMap<OAuth, AuthClientIfs> authClientMaps = new EnumMap<>(OAuth.class);
        authClientsList.forEach(client -> authClientMaps.put(client.getPlatform(), client));
        this.authClientsMap = authClientMaps;
    }


    public AuthClientIfs getClient(OAuth oAuth) {
        return authClientsMap.get(oAuth);
    }
}
