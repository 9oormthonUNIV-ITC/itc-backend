package com.backend.itcuniv.domain.users.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
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
}
