package com.backend.itcuniv.domain.users.dto.request;

import lombok.Getter;

@Getter
public class CreateUserRequestDto {
    private Long id;
    private String email;
    private String name;
    private String givenName;
    private String familyName;
    private String picture;

    public CreateUserRequestDto(String email, String name, String givenName, String familyName, String picture) {
        this.email = email;
        this.name = name;
        this.givenName = givenName;
        this.familyName = familyName;
        this.picture = picture;
    }
}
