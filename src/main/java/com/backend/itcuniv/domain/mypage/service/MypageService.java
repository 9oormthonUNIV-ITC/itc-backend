package com.backend.itcuniv.domain.mypage.service;

import com.backend.itcuniv.domain.mypage.dto.response.MypageResponseDto;
import com.backend.itcuniv.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MypageService {

    UserRepository userRepository;

    public MypageResponseDto getMyInfo(String adminToken) {
        String userName = userRepository.findUserNameByGoogleId(adminToken);
        String email = userRepository.findEmailByGoogleId(adminToken);

        return new MypageResponseDto(userName, email);
    }

    @Transactional
    public MypageResponseDto updateMyInfo(String adminToken) {
        String userName = userRepository.findUserNameByGoogleId(adminToken);
        String email = userRepository.findEmailByGoogleId(adminToken);

        MypageResponseDto mypageResponseDto = new MypageResponseDto(userName, email);
        mypageResponseDto.update(userName);

        return mypageResponseDto;
    }
}
