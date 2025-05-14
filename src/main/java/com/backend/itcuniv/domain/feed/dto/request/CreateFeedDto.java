package com.backend.itcuniv.domain.feed.dto.request;

import lombok.Getter;

@Getter
public class CreateFeedDto {
    private String adminToken;  // 프론트에서 닉네임 받기
    private String link;
}
