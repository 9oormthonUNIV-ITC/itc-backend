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
    private Long id;

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

    public User(String email, String name, String givenName, String familyName, String picture) {
        this.email = email;
        this.name = name;
        this.givenName = givenName;
        this.familyName = familyName;
        this.picture = picture;
    }
}
