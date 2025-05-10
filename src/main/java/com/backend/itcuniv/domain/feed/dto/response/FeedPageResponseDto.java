package com.backend.itcuniv.domain.feed.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedPageResponseDto {
    private List<FeedResponseDto> content;
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;
    private int numberOfElements;
    private boolean first;
    private boolean last;
    private boolean empty;
}
