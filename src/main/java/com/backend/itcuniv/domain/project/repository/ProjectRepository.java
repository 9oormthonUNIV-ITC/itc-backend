package com.backend.itcuniv.domain.project.repository;

import com.backend.itcuniv.domain.project.entity.ProjectPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectPost, Long> {

}
