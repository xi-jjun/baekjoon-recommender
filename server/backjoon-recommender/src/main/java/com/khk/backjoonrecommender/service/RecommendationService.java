package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.request.SettingRequestDto;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public interface RecommendationService {
    public BasicResponseDto<?> recommendProblem(Authentication authentication);

    public BasicResponseDto<?> checkProblemIfSolved(Authentication authentication, Long problemId) throws IOException;

    public BasicResponseDto<?> findAdditionalProblem(Authentication authentication, SettingRequestDto settingRequestDto) throws IOException;

    public BasicResponseDto<?> reloadProblem(Authentication authentication) throws IOException;
}
