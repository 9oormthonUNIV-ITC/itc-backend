package com.backend.itcuniv.domain.users.dto.response;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    @Column(name = "email")
    private String email; // 이메일
    @Column(name = "name")
    private String name; // 이름
//    @Column(name = "given_name")
//    private String givenName; // 이름
//    @Column(name = "family_name")
//    private String familyName; // 성
//    @Column(name = "picture")
//    private String picture; // 프로필 사진

    public LoginResponseDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
