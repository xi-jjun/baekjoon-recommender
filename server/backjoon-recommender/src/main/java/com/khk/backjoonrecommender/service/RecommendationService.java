package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.request.SettingRequestDto;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.RecommendProblemResponseDto;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.List;

public interface RecommendationService {
    BasicResponseDto<?> recommendProblem(Authentication authentication);

    BasicResponseDto<List<RecommendProblemResponseDto>> getTodayRecommendedProblemListByUser(Authentication authentication);

    BasicResponseDto<?> checkProblemIfSolved(Authentication authentication, Long problemId) throws IOException;

    BasicResponseDto<?> recommendAdditionalProblem(Authentication authentication, SettingRequestDto settingRequestDto);

    BasicResponseDto<?> reloadProblem(Authentication authentication) throws IOException;
}
