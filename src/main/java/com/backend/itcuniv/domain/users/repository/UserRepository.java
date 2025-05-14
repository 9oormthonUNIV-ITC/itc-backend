package com.backend.itcuniv.domain.users.repository;

import com.backend.itcuniv.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByGoogleId(String googleId);

    Optional<User> findIdByNickname(String nickname);

    @Query("SELECT u.id FROM User u WHERE u.googleId = :googleId")
    Long findIdByGoogleId(@Param("googleId") String googleId);

    @Query("SELECT u.adminToken FROM User u WHERE u.googleId = :googleId")
    Integer findAdminTokenByGoogleId(@Param("googleId") String googleId);

}
