package com.backend.itcuniv.domain.users.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GoogleUserInfo {
    private String code;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private final String grantType = "authorization_code";
}
