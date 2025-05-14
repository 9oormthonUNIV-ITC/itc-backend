package com.backend.itcuniv.domain.project.dto.request;

import lombok.Getter;

@Getter
public class CreateProjectRequestDto {

    private String token;
    private String title;
    private String summery;
    private String content;
    private String boardPicture;
    private String team;
}
