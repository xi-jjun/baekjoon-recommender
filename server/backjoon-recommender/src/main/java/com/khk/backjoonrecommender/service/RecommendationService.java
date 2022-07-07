package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public interface RecommendationService {
    public BasicResponseDto<?> recommendProblem(Authentication authentication) throws IOException;

    public BasicResponseDto<?> checkProblemIfSolved();

    public BasicResponseDto<?> findAdditionalProblem();

    public BasicResponseDto<?> reloadProblem();
}
