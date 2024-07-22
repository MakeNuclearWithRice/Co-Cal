package com.cocal.auth.controller;

import com.cocal.auth.service.AuthService;
import com.cocal.auth.dto.User;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthRestController {


    private final AuthService authService;

    @GetMapping("/{platform}/uri") // "/uri/{}" spring 전략 패턴 - map으로
    public void generateUrl(HttpServletResponse response,@PathVariable String platform) throws IOException {
        // TODO Oauth 인증 URL 생성 후 전송

        response.sendRedirect(authService.generateUrl(platform));
    }


    @GetMapping("/{platform}/login")
    public User authLogin(@RequestParam(value = "code", required = false) String code,
                          @RequestParam(value = "error", required = false) String error,
                          @PathVariable String platform,
                          HttpServletResponse response) throws IOException {
        // TODO AuthCode 받은 후 TokenRequest 와 함께 전송
        if(code == null) {
            response.sendRedirect("/error");
        }

        return authService.getUserInfo(platform,authService.getToken(platform, code));

    }


    // TODO AccessToken 받은 후 email 가져오기

}

