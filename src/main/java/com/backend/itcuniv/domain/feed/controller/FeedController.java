package com.backend.itcuniv.domain.feed.controller;

import com.backend.itcuniv.domain.feed.dto.request.CreateFeedDto;
import com.backend.itcuniv.domain.feed.dto.response.FeedPageResponseDto;
import com.backend.itcuniv.domain.feed.dto.response.FeedResponseDto;
import com.backend.itcuniv.domain.feed.service.FeedService;
import com.backend.itcuniv.domain.project.dto.response.ProjectPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feedService;

    // 피드 생성
    @PostMapping("/write")
    public ResponseEntity<?> createFeed(@RequestBody CreateFeedDto dto) {
        // 권한 확인
        if(!feedService.editAuth(dto.getAdminToken())){
            return ResponseEntity.status(403).body("권한이 없습니다.");
        }

        return feedService.feedSave(dto);
    }

    // 피드 수정
    @PatchMapping("/{feed_id}")
    public ResponseEntity<?> updateFeed(@PathVariable Long feed_id, @RequestBody CreateFeedDto dto) {
        // 권한 확인
        if(!feedService.editAuth(dto.getAdminToken())){
            return ResponseEntity.status(403).body("권한이 없습니다.");
        }

        return feedService.feedUpdate(feed_id, dto.getLink());
    }

    // 피드 삭제
    @DeleteMapping("/{feed_id}")
    public ResponseEntity<?> deleteFeed(@PathVariable Long feed_id, @RequestBody CreateFeedDto dto) {
        // 권한 확인
        if(!feedService.editAuth(dto.getAdminToken())){
            return ResponseEntity.status(403).body("권한이 없습니다.");
        }

        return feedService.feedDelete(feed_id);
    }

    // 피드 리스트
    @GetMapping("/list")
    public ResponseEntity<FeedPageResponseDto> getFeedList(
            @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        FeedPageResponseDto dto = feedService.getAllFeedPosts(pageable);

        return ResponseEntity.ok(dto);
    }

    // 피드 단일 조회
    @GetMapping("/{feed_id}")
    public ResponseEntity<FeedResponseDto> getFeed(@PathVariable Long feed_id) {
        return ResponseEntity.ok(feedService.getFeed(feed_id));
    }
}
