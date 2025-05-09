package com.backend.itcuniv.domain.project.service;

import com.backend.itcuniv.domain.project.dto.request.CreateProjectRequestDto;
import com.backend.itcuniv.domain.project.entity.ProjectPost;
import com.backend.itcuniv.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        ProjectPost projectPost = new ProjectPost(
                id,
                dto.getNickname(),
                dto.getTitle(),
                dto.getSummery(),
                dto.getContent(),
                dto.getBoardPicture(),
                dto.getTeam());

        try {
            projectRepository.save(projectPost);
            projectRepository.flush();  // 강제 DB 반영
        } catch (Exception e) {
            System.out.println("❌ 저장 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
