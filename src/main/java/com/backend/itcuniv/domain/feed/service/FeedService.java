package com.backend.itcuniv.domain.feed.service;

import com.backend.itcuniv.domain.feed.dto.request.CreateFeedDto;
import com.backend.itcuniv.domain.feed.dto.response.FeedPageResponseDto;
import com.backend.itcuniv.domain.feed.dto.response.FeedResponseDto;
import com.backend.itcuniv.domain.feed.entity.Feed;
import com.backend.itcuniv.domain.feed.repository.FeedRepository;
import com.backend.itcuniv.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;
    private final UserRepository userRepository;

    // 권한 확인
    public boolean editAuth(String token) {
        Integer adminToken = userRepository.findAdminTokenByGoogleId(token).getFirst().getAdminToken();

        return (adminToken == 1);
    }

    // 피드 생성
    @Transactional
    public ResponseEntity<?> feedSave(CreateFeedDto dto) {
        Long user_id = userRepository.findIdByGoogleId(dto.getAdminToken()).getFirst().getId();

        Feed feed = new Feed(user_id, dto.getLink());

        feedRepository.save(feed);

        return ResponseEntity.ok("피드가 생성되었습니다.");
    }

    // 피드 삭제
    @Transactional
    public ResponseEntity<?> feedDelete(Long feed_id) {
        Feed feed = feedRepository.findById(feed_id)
                .orElseThrow(() -> new RuntimeException("피드 없음"));

        feedRepository.delete(feed);

        return ResponseEntity.ok("피드가 삭제되었습니다.");
    }

    // 피드 수정
    @Transactional
    public ResponseEntity<String> feedUpdate(Long id, String link) {
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("피드 없음"));

        feed.update(link);

        return ResponseEntity.ok("피드가 수정되었습니다.");
    }

    // 피드 전체 조회
    public FeedPageResponseDto getAllFeedPosts(Pageable pageable) {
        Page<Feed> page = feedRepository.findAll(pageable);

        List<FeedResponseDto> content = page.stream()
                .map(feed -> new FeedResponseDto(
                        feed.getId(),
                        feed.getUserId(),
                        feed.getLink()
                ))
                .toList();

        FeedPageResponseDto result = new FeedPageResponseDto(
                content,
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber(),
                page.getNumberOfElements(),
                page.isFirst(),
                page.isLast(),
                page.isEmpty()
        );

        return result;
    }

    // 피드 단일 조회
    public FeedResponseDto getFeed(Long id) {
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("피드 없음"));

        return new FeedResponseDto(
                feed.getId(),
                feed.getUserId(),
                feed.getLink()
        );
    }
}
