package com.khk.backjoonrecommender.service.impl;

import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.MyPageResponseDto;
import com.khk.backjoonrecommender.repository.UserRepository;
import com.khk.backjoonrecommender.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BasicUserService implements UserService {
    private final UserRepository userRepository;

    public BasicResponseDto<MyPageResponseDto> findUser() {
        return null;
    }

    public BasicResponseDto<?> registerUser() {
        return null;
    }

    public BasicResponseDto<?> modifyUser() {
        return null;
    }
}
