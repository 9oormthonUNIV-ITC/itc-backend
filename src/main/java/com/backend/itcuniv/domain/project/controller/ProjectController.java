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

    private final UserService userService;
    private final ProjectService projectService;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    // 게시글 작성
    @PostMapping("/write")
    public ResponseEntity<?> createProjectPost(@RequestBody CreateProjectRequestDto createProjectRequestDto) {
        // 작성자 권한 확인
        // todo: 권한 확인은 nickname이 아닌 token 으로 변경
        if(!projectService.editAuth(createProjectRequestDto.getNickname())) {
            return ResponseEntity.status(403).body("권한이 없습니다.");
        }

        // DB에 넣을 작성자 id 가져오기
        Long id = userRepository.findIdByNickname(createProjectRequestDto.getNickname())
                .orElseThrow(() -> new RuntimeException("사용자 없음"))
                .getId();

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

    // 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<ProjectPostResponseDto> getProjectPost(@PathVariable Long postId) {
        ProjectPostResponseDto dto = projectService.getProjectPost(postId);

        return ResponseEntity.ok(dto);
    }

    // 게시글 수정
    @PostMapping("/edit/{postId}")
    public ResponseEntity<?> editProjectPost(@PathVariable Long postId, @RequestBody CreateProjectRequestDto dto) {
        // 게시글 찾기
        ProjectBoard post = projectRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        // 원본 작성자
        String author = projectRepository.findNicknameById(postId);

        // 수정자 권한 확인
        if(!dto.getNickname().equals(author)) {
            return ResponseEntity.status(403).body("작성자와 수정자가 일치하지 않습니다.");
        }

        // DB에 넣을 작성자 id 가져오기
        Long id = userRepository.findIdByNickname(dto.getNickname())
                .orElseThrow(() -> new RuntimeException("사용자 없음"))
                .getId();

        // DB에 저장
        projectService.saveProjectPost(id, dto);

        return ResponseEntity.ok("게시글 수정 완료");
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<?> deleteProjectPost(@PathVariable Long postId, @RequestBody String nickname) {

        // 게시글 찾기
        ProjectBoard post = projectRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        // 원본 작성자
        String author = projectRepository.findNicknameById(postId);

        // 삭제자 권한 확인
        if(!author.equals(nickname)) {
            return ResponseEntity.status(403).body("작성자와 삭제자가 일치하지 않습니다.");
        }

        // 게시글 삭제
        projectRepository.deleteById(postId);

        return ResponseEntity.ok("게시글 삭제 완료");
    }
}
