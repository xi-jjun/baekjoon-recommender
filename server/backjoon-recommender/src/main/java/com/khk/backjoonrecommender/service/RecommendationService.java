package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface RecommendationService {
    public BasicResponseDto<?> recommendProblem();

    public BasicResponseDto<?> checkProblemIfSolved();

    public BasicResponseDto<?> findAdditionalProblem();

    public BasicResponseDto<?> reloadProblem();
}
