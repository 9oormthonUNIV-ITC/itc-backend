package com.backend.itcuniv.domain.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project")
@Getter
@NoArgsConstructor
public class ProjectBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "title")
    private String title;
    @Column(name = "summery")
    private String summery;
    @Column(name = "content")
    private String content;
    @Column(name = "project_picture")
    private String projectPicture;
    @Column(name = "team" )
    private String team;

    public ProjectBoard(
            Long id,
            String title,
            String summery,
            String content,
            String projectPicture,
            String team
    ) {
        this.userId = id;
        this.title = title;
        this.summery = summery;
        this.content = content;
        this.projectPicture = projectPicture;
        this.team = team;
    }

    public void update(String title, String summery, String content, String projectPicture, String team) {
        this.title = title;
        this.summery = summery;
        this.content = content;
        this.projectPicture = projectPicture;
        this.team = team;
    }
}
