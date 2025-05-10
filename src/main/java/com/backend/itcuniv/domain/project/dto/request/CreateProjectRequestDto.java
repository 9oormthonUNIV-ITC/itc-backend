package com.backend.itcuniv.domain.project.dto.request;

import lombok.Getter;

@Getter
public class CreateProjectRequestDto {

    private String nickname;  // todo: 프론트에서 토큰 받는걸로 변경
    private String title;
    private String summery;
    private String content;
    private String boardPicture;
    private String team;
}
