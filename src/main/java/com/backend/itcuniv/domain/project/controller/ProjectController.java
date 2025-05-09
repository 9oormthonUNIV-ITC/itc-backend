package com.backend.itcuniv.domain.project.controller;

import com.backend.itcuniv.domain.project.dto.request.CreateProjectRequestDto;
import com.backend.itcuniv.domain.project.repository.ProjectRepository;
import com.backend.itcuniv.domain.project.service.ProjectService;
import com.backend.itcuniv.domain.users.repository.UserRepository;
import com.backend.itcuniv.domain.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project-board")
public class ProjectController {

    private final UserService userService;
    private final ProjectService projectService;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @PostMapping("/write")
    public ResponseEntity<?> createProjectPost(@RequestBody CreateProjectRequestDto createProjectRequestDto) {
        // 작성자 권한 확인
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
}
