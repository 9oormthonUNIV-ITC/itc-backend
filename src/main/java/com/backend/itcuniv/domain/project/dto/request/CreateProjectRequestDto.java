package com.backend.itcuniv.domain.project.dto.request;

import lombok.Getter;

@Getter
public class CreateProjectRequestDto {

    private String nickname;  // 프론트에서 닉네임 받기
    private String title;
    private String summery;
    private String content;
    private String boardPicture;
    private String team;
}
