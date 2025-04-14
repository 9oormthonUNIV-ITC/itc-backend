package com.backend.itcuniv.domain.users.service;

import com.backend.itcuniv.domain.users.dto.request.CreateUserRequestDto;
import com.backend.itcuniv.domain.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;    // 비밀번호 암호화

//    @Autowired
//    private TokenProvider tokenProvider;

   // 사용자 정보 저장 로직
    public void saveUser(CreateUserRequestDto dto) {
        User user = new User();
        user.setKakaoId(dto.getKakaoId());
        user.setNickname(dto.getNickname());

        userRepository.save(user);
    }
}
