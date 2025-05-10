package com.backend.itcuniv.domain.project.repository;

import com.backend.itcuniv.domain.project.entity.ProjectBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectBoard, Long> {
    String findNicknameById(Long project_id);
}
