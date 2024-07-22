package com.cocal.auth.client;

import com.cocal.auth.dto.User;
import com.cocal.auth.service.OAuth;

public interface AuthClientIfs {
    String generateUrl();

    String getToken(String code);

    User getUserInfo(String accessToken);

    OAuth getPlatform();

}
