package com.backend.itcuniv.domain.mypage.service;

import com.backend.itcuniv.domain.mypage.dto.response.MypageResponseDto;
import com.backend.itcuniv.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final UserRepository userRepository;

    public MypageResponseDto getMyInfo(String adminToken) {
        String userName = userRepository.findNicknameByGoogleId(adminToken).getFirst().getNickname();
        String email = userRepository.findEmailByGoogleId(adminToken).getFirst().getEmail();

        return new MypageResponseDto(userName, email);
    }

    @Transactional
    public MypageResponseDto updateMyInfo(String adminToken) {
        String userName = userRepository.findNicknameByGoogleId(adminToken).getFirst().getNickname();
        String email = userRepository.findEmailByGoogleId(adminToken).getFirst().getEmail();

        MypageResponseDto mypageResponseDto = new MypageResponseDto(userName, email);
        mypageResponseDto.update(userName);

        return mypageResponseDto;
    }
}
