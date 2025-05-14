package com.backend.itcuniv.domain.users.dto.response;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private String googleId;
    private String email; // 이메일
    private String name; // 이름

    public LoginResponseDto(String googleId, String email, String name) {
        this.googleId = googleId;
        this.email = email;
        this.name = name;
    }
}
