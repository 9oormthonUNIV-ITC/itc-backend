package com.backend.itcuniv.domain.feed.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "feed")
@Getter
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="user_id")
    private Long userId;
    @Column(name="link")
    private String link;

    public Feed() {}

    public Feed(Long userId, String link) {
        this.userId = userId;
        this.link = link;
    }

    public void update(String link) {
        this.link = link;
    }
}
