package com.backend.itcuniv.domain.users.repository;

import com.backend.itcuniv.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUserId(String userId);

    Optional<User> findIdByNickname(String nickname);

    Long findIdByUserId(String token);

    Integer findAuthByUserId(String token);
}
