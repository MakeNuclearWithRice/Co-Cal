package com.cocal.auth.controller;

import com.cocal.auth.client.GoogleClient;
import com.cocal.auth.client.NaverClient;
import com.cocal.auth.dto.User;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthRestController {

    private final GoogleClient googleClient;
    private final NaverClient naverClient;

    @GetMapping("/uri") // "/uri/{}" spring 전략 패턴 - map으로
    public void generateUrl(HttpServletResponse response) throws IOException {
        // TODO Oauth 인증 URL 생성 후 전송

        response.sendRedirect(naverClient.generateUrl());

//      GOOGLE LOGIN
        //response.sendRedirect(googleClient.generateUrl());
    }


    @GetMapping("/login")
    public User authLogin(@RequestParam(value = "code", required = false) String code,
                          @RequestParam(value = "error", required = false) String error,
                          HttpServletResponse response) throws IOException {
        // TODO AuthCode 받은 후 TokenRequest 와 함께 전송



        if(code == null) {
            response.sendRedirect("/error");
        }

//      GOOGLE LOGIN
//
//        return googleClient.getUserInfo(googleClient.getToken(code));
        return null;
    }


    // TODO AccessToken 받은 후 email 가져오기

}
// 초급개발자들한테 원하는거 어짜피 회사가서 일하면 api상하차작업을 엄청 한데요 그래서 api다루는건 당연히 기본이고 근데 api그대로 베겨오는거 말고
// 들고와서 니가 배운대로 효율적으로 사용할 수 있냐를 묻는다고
// 그래서 이것도 그냥 그대로 배끼지말고 배운 디자인패턴 여기다가 접목시키라고
// 그래서 인터페이스를 만드는데 자꾸 집착하는건데
// 어떻게 해야할지 머리가 안돌아감요
// 결곡 이거 디자인패턴으로 만들려면
