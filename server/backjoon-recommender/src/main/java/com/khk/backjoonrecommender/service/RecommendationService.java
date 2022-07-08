package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.request.SettingRequestDTO;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public interface RecommendationService {
    public BasicResponseDto<?> recommendProblem(Authentication authentication) throws IOException;

    public BasicResponseDto<?> checkProblemIfSolved();

    public BasicResponseDto<?> findAdditionalProblem(Authentication authentication, SettingRequestDTO settingRequestDTO) throws IOException;

    public BasicResponseDto<?> reloadProblem(Authentication authentication) throws IOException;
}
