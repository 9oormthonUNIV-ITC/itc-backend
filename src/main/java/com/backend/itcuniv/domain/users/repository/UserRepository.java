package com.backend.itcuniv.domain.users.repository;

import com.backend.itcuniv.domain.users.dto.response.IntegerAdminTokenDto;
import com.backend.itcuniv.domain.users.dto.response.LongIdDto;
import com.backend.itcuniv.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByGoogleId(String googleId);

    List<LongIdDto> findIdByGoogleId(String googleId);

    List<IntegerAdminTokenDto> findAdminTokenByGoogleId(String googleId);

    String findNicknameByGoogleId(String googleId);

    String findEmailByGoogleId(String googleId);
}
