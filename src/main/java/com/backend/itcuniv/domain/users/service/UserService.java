package com.backend.itcuniv.domain.users.service;

import com.backend.itcuniv.domain.users.dto.request.CreateUserRequestDto;
import com.backend.itcuniv.domain.users.dto.request.GoogleRequestAccessTokenDto;
import com.backend.itcuniv.domain.users.dto.response.LoginResponseDto;
import com.backend.itcuniv.domain.users.entity.User;
import com.backend.itcuniv.domain.users.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;    // 비밀번호 암호화

//    @Autowired
//    private TokenProvider tokenProvider;

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.client.secret}")
    private String googleClientSecret;

    @Value("${google.redirect_uri}")
    private String googleRedirectUri;

   // 사용자 정보 저장 로직
    public void saveUser(CreateUserRequestDto dto) {
        User user = new User(
                dto.getId(),
                dto.getEmail(),
                dto.getName(),
                dto.getGivenName(),
                dto.getFamilyName(),
                dto.getPicture()
        );

        userRepository.save(user);
    }

    // 로그인 Url 생성
    public String getGoogleLoginUrl() {
        return "https://accounts.google.com/o/oauth2/v2/auth?client_id="
                + googleClientId
                + "&redirect_uri="
                + googleRedirectUri
                + "&response_type=code&scope=email profile";
    }


    // AccessToken Url
    public String getAccessToken(String code) {
        GoogleRequestAccessTokenDto requestDto = new GoogleRequestAccessTokenDto(
                code, googleClientId, googleClientSecret, googleRedirectUri
        );

        String tokenUrl = "https://oauth2.googleapis.com/token";

        // 1. 파라미터 구성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", requestDto.getCode());
        body.add("client_id", requestDto.getClientId());
        body.add("client_secret", requestDto.getClientSecret());
        body.add("redirect_uri", requestDto.getRedirectUri());
        body.add("grant_type", requestDto.getGrantType());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<JsonNode> response = new RestTemplate().postForEntity(tokenUrl, request, JsonNode.class);

        return response.getBody().get("access_token").asText();
    }

    // 사용자 정보 Url
    public LoginResponseDto getUserInfo(String accessToken) {
        String url = "https://www.googleapis.com/userinfo/v2/me";

        // 1, 헤더에 access token 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        // 2. HttpEntity 생성 (본문 없이 헤더만 설정)
        HttpEntity<String> request = new HttpEntity<>(headers);

        // 3. RestTemplate으로 요청 전송
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class
        );

        System.out.println("Response Body: " + response.getBody());


        ObjectMapper objectMapper = new ObjectMapper();
        CreateUserRequestDto userInfo;

        try {
            userInfo = objectMapper.readValue(response.getBody(), CreateUserRequestDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Service User Info: " + userInfo);

        // 4. DB에 사용자 정보 저장
        saveUser(userInfo);


        // 5. 응답 반환
        return new LoginResponseDto(
                userInfo.getEmail(),
                userInfo.getName()
        );
    }
}
