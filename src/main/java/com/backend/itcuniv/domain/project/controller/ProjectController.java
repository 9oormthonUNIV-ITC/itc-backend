package com.backend.itcuniv.domain.project.controller;

import com.backend.itcuniv.domain.project.dto.request.CreateProjectRequestDto;
import com.backend.itcuniv.domain.project.dto.response.ProjectPageResponseDto;
import com.backend.itcuniv.domain.project.dto.response.ProjectPostResponseDto;
import com.backend.itcuniv.domain.project.entity.ProjectBoard;
import com.backend.itcuniv.domain.project.repository.ProjectRepository;
import com.backend.itcuniv.domain.project.service.ProjectService;
import com.backend.itcuniv.domain.users.repository.UserRepository;
import com.backend.itcuniv.domain.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project-board")
public class ProjectController {

    private final ProjectService projectService;
    private final UserRepository userRepository;

    // 게시글 작성
    @PostMapping("/write")
    public ResponseEntity<?> createProjectPost(@RequestBody CreateProjectRequestDto createProjectRequestDto) {
        // 작성자 권한 확인
        if(!projectService.editAuth(createProjectRequestDto.getAdminToken())) {
            return ResponseEntity.status(403).body("권한이 없습니다.");
        }

        // DB에 넣을 작성자 id 가져오기
        Long id = userRepository.findIdByGoogleId(createProjectRequestDto.getAdminToken());

        // DB에 저장
        projectService.saveProjectPost(id, createProjectRequestDto);

        return ResponseEntity.ok("게시글 작성 완료");
    }

    // 프로젝트 게시글 리스트
    @GetMapping("/list")
    public ResponseEntity<ProjectPageResponseDto> getAllProjectPosts(
            @PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        ProjectPageResponseDto dto = projectService.getAllProjectPosts(pageable);

        return ResponseEntity.ok(dto);
    }

    // 게시글 단일 조회
    @GetMapping("/{postId}")
    public ResponseEntity<ProjectPostResponseDto> getProjectPost(@PathVariable Long postId) {
        ProjectPostResponseDto dto = projectService.getProjectPost(postId);

        return ResponseEntity.ok(dto);
    }

    // 게시글 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<?> editProjectPost(@PathVariable Long postId, @RequestBody CreateProjectRequestDto dto) {
        // 작성자 권한 확인
        if(!projectService.editAuth(dto.getAdminToken())) {
            return ResponseEntity.status(403).body("권한이 없습니다.");
        }

        return projectService.editProjectPost(postId, dto);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deleteProjectPost(@PathVariable Long postId, @RequestBody String adminToken) {
        // 작성자 권한 확인
        if(!projectService.editAuth(adminToken)) {
            return ResponseEntity.status(403).body("권한이 없습니다.");
        }

        return projectService.deleteProjectPost(postId);
    }
}
