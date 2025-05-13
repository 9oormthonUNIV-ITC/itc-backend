package com.backend.itcuniv.domain.users.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "google_id", unique = true)
    private String userId; // 구글에서 제공하는 고유 ID
    @Column(name = "nickname", unique = true)
    private String nickname;
    @Column(name = "email")
    private String email; // 이메일
    @Column(name = "name")
    private String name; // 이름
    @Column(name = "given_name")
    private String givenName; // 이름
    @Column(name = "family_name")
    private String familyName; // 성
    @Column(name = "picture")
    private String picture; // 프로필 사진

    public User(String id, String nickname, String email, String name, String givenName, String familyName, String picture) {
        this.userId = id;
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.givenName = givenName;
        this.familyName = familyName;
        this.picture = picture;
    }

    public User() {}
}
