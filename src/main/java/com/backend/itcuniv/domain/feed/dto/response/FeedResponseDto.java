package com.backend.itcuniv.domain.feed.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedResponseDto {
    private Long id;
    private Long userId;
    private String link;
}
