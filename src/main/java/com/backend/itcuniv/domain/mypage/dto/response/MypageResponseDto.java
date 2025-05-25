package com.backend.itcuniv.domain.mypage.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MypageResponseDto {
    String userName;
    String email;

    public MypageResponseDto(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public void update(String userName) {
        this.userName = userName;
    }
}
