package com.backend.itcuniv.domain.project.service;

import com.backend.itcuniv.domain.project.dto.request.CreateProjectRequestDto;
import com.backend.itcuniv.domain.project.entity.ProjectBoard;
import com.backend.itcuniv.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
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

    // 작성자 권한 확인
    public boolean editAuth(String nickname) {
        return nickname.equals("9oormthonuniv.inhatc");
    }

    // Controller에서 작성자 정보 받아서 DB에서 가져온 다음에 id로 같이 전송
    @Transactional
    public void saveProjectPost(Long id, CreateProjectRequestDto dto) {
        ProjectBoard projectBoard = new ProjectBoard(
                id,
                dto.getNickname(),
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

    public ResponseEntity<List<ProjectBoard>> getAllProjectPosts(){
        // 게시글 리스트 가져오기
        List<ProjectBoard> projectBoards = projectRepository.findAll();
        if (projectBoards.isEmpty()) {
            System.out.println("게시글이 없습니다.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        System.out.println("게시글 리스트:");
        for (ProjectBoard post : projectBoards) {
            System.out.println(post);
        }

       return new ResponseEntity<>(projectBoards, HttpStatus.OK);
    }
}
