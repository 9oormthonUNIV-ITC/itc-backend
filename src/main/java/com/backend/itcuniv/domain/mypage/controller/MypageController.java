package com.backend.itcuniv.domain.mypage.controller;

import com.backend.itcuniv.domain.mypage.dto.response.MypageResponseDto;
import com.backend.itcuniv.domain.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;

    // 정보 반환
    @PostMapping("/info")
    public MypageResponseDto getInfo(@RequestBody String adminToken) {
        // 만약 정보가 잘못되었거나 없다면 return 어떻게 보내야할지?
        return mypageService.getMyInfo(adminToken);
    }

    // 정보 수정
    @PatchMapping("/info")
    public MypageResponseDto updateInfo(@RequestBody String adminToken) {
        return mypageService.updateMyInfo(adminToken);
    }
}
