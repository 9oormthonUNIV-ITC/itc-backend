package com.backend.itcuniv.domain.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPostResponseDto {
    private Long id;
    private Long userId;
    private String title;
    private String summery;
    private String content;
    private String projectPicture;
    private String team;
}