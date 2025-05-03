package com.backend.itcuniv.domain.users.controller;

import com.backend.itcuniv.domain.users.dto.response.LoginResponseDto;
import com.backend.itcuniv.domain.users.repository.UserRepository;
import com.backend.itcuniv.domain.users.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

//    private final TokenProvider tokenProvider;

    // 구글 로그인
    @RequestMapping("/login")
    public void redirectToGoogleLogin(HttpServletResponse response) throws IOException {
        // 1. 구글 로그인 URL 생성
        String googleLoginUrl = userService.getGoogleLoginUrl();

        // 2. 구글 로그인 페이지로 리다이렉트
        response.sendRedirect(googleLoginUrl);
    }

    @GetMapping("/callback")
//    public ResponseEntity<LoginResponseDto> googleCallback(@RequestParam("code") String code) {
    public LoginResponseDto googleCallback(@RequestParam("code") String code) {
        // 1. 인가 코드 확인
//        System.out.println("Authorization Code: " + code);

        // 2. 구글 서버에 인가 코드로 access token 요청
        String accessToken = userService.getAccessToken(code);
//        System.out.println("Access Token: " + accessToken);

        // 3. access token으로 사용자 정보 요청
        LoginResponseDto userResponse = userService.getUserInfo(accessToken);
//        System.out.println("User Info: " + userInfo);

        // return문 -> id 이름 메일 -> db에도 저장
        return userResponse;
    }
}
