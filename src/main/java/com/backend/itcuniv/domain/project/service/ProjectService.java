package com.backend.itcuniv.domain.project.service;

import com.backend.itcuniv.domain.project.dto.request.CreateProjectRequestDto;
import com.backend.itcuniv.domain.project.dto.response.ProjectPageResponseDto;
import com.backend.itcuniv.domain.project.dto.response.ProjectPostResponseDto;
import com.backend.itcuniv.domain.project.entity.ProjectBoard;
import com.backend.itcuniv.domain.project.repository.ProjectRepository;
import com.backend.itcuniv.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ApplicationContext applicationContext;
    private final ApplicationArguments applicationArguments;
    private final UserRepository userRepository;

    // 작성자 권한 확인
    public boolean editAuth(String token) {
        Long adminToken = userRepository.findIdByGoogleId(token).getFirst().getId();

        return (adminToken == 1);
    }

    // Controller에서 작성자 정보 받아서 DB에서 가져온 다음에 id로 같이 전송
    @Transactional
    public void saveProjectPost(Long id, CreateProjectRequestDto dto) {
        ProjectBoard projectBoard = new ProjectBoard(
                id,
                dto.getTitle(),
                dto.getSummery(),
                dto.getContent(),
                dto.getBoardPicture(),
                dto.getTeam()
        );

        try {
            projectRepository.save(projectBoard);
            projectRepository.flush();  // 강제 DB 반영
        } catch (Exception e) {
            System.out.println("❌ 저장 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
        }

    }

    // 프로젝트 게시글 리스트
    public ProjectPageResponseDto getAllProjectPosts(Pageable pageable) {
        Page<ProjectBoard> page = projectRepository.findAll(pageable);

        List<ProjectPostResponseDto> content = page.stream()
                .map(post -> new ProjectPostResponseDto(
                        post.getId(),
                        post.getUserId(),
                        post.getTitle(),
                        post.getSummery(),
                        post.getContent(),
                        post.getProjectPicture(),
                        post.getTeam()
                ))
                .toList();

        ProjectPageResponseDto result = new ProjectPageResponseDto(
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

    // 프로젝트 게시글 단일조회
    public ProjectPostResponseDto getProjectPost(Long id) {
        ProjectBoard projectBoard = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        return new ProjectPostResponseDto(
                projectBoard.getId(),
                projectBoard.getUserId(),
                projectBoard.getTitle(),
                projectBoard.getSummery(),
                projectBoard.getContent(),
                projectBoard.getProjectPicture(),
                projectBoard.getTeam()
        );
    }

    // 프로젝트 게시글 수정
    @Transactional
    public ResponseEntity<?> editProjectPost(Long id, CreateProjectRequestDto dto) {
        ProjectBoard projectBoard = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        // 수정자 권한 확인
        if(!editAuth(dto.getAdminToken())) {
            return ResponseEntity.status(403).body("권한이 없습니다.");
        }

        // 작성자, 수정자 일치 확인
        if(!projectBoard.getUserId().equals(userRepository.findIdByGoogleId(dto.getAdminToken()))) {
            return ResponseEntity.status(403).body("작성자와 수정자가 다릅니다.");
        }

        projectBoard.update(
                dto.getTitle(),
                dto.getSummery(),
                dto.getContent(),
                dto.getBoardPicture(),
                dto.getTeam()
        );

        return ResponseEntity.ok("게시글 수정 완료");
    }

    // 프로젝트 게시글 삭제
    @Transactional
    public ResponseEntity<?> deleteProjectPost(Long id) {
        // 게시글 찾기
        ProjectBoard post = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        // 게시글 삭제
        projectRepository.delete(post);

        return ResponseEntity.ok("게시글 삭제 완료");
    }
}
