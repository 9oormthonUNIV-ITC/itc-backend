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
    private String googleId; // 구글에서 제공하는 고유 ID
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
    @Column(name = "admin_token")
    private int adminToken; // 권한 (0: 일반 사용자, 1: 관리자)

    public User(String id, String nickname, String email, String name, String givenName, String familyName, String picture) {
        this.googleId = id;
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.givenName = givenName;
        this.familyName = familyName;
        this.picture = picture;
        this.adminToken = 0;
    }

    public User() {}
}
