package com.backend.itcuniv.domain.project.controller;

import com.backend.itcuniv.domain.project.repository.ProjectRepository;
import com.backend.itcuniv.domain.project.service.ProjectService;
import com.backend.itcuniv.domain.users.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project-board")
public class ProjectController {

    UserRepository userRepository;
    ProjectService projectService;
    ProjectRepository projectRepository;


}
