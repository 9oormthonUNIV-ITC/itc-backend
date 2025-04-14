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
}
