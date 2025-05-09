package com.backend.itcuniv.domain.project.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "projectBoard")
@Getter
public class ProjectPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long project_id;

    @Column(name = "id")
    private Long id;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "title")
    private String title;
    @Column(name = "summery")
    private String summery;
    @Column(name = "content")
    private String content;
    @Column(name = "project_picture")
    private String project_picture;
    @Column(name = "team" )
    private String team;

    public ProjectPost(Long id, String nickname, String title, String summery, String content, String project_picture, String team) {
        this.id = id;
        this.nickname = nickname;
        this.title = title;
        this.summery = summery;
        this.content = content;
        this.project_picture = project_picture;
        this.team = team;
    }

    public ProjectPost() {}
}
