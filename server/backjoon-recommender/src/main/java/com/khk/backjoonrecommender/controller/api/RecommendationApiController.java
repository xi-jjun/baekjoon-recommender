package com.khk.backjoonrecommender.controller.api;

import com.khk.backjoonrecommender.controller.dto.request.CheckSolvedRequestDto;
import com.khk.backjoonrecommender.controller.dto.request.SettingRequestDto;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api/v1/recommendation")
@RestController
public class RecommendationApiController {

    private final RecommendationService recommendationService;

    @GetMapping
    public BasicResponseDto<?> recommendProblem(Authentication authentication) {
        return recommendationService.recommendProblem(authentication);
    }

    @GetMapping("/today")
    public BasicResponseDto<?> todayRecommendedProblemList(Authentication authentication) {
        return recommendationService.getTodayRecommendedProblemListByUser(authentication);
    }

    @PostMapping
    public BasicResponseDto<?> problemCheck(Authentication authentication, @RequestBody CheckSolvedRequestDto checkSolvedRequestDto) throws IOException {
        Long problemId = checkSolvedRequestDto.getProblemId();
        return recommendationService.checkProblemIfSolved(authentication, problemId);
    }

    @PostMapping("/additional")
    public BasicResponseDto<?> additionalProblemDetails(Authentication authentication, @RequestBody SettingRequestDto settingRequestDto) {
        return recommendationService.recommendAdditionalProblem(authentication, settingRequestDto);
    }

    @GetMapping("/refresh")
    public BasicResponseDto<?> refreshRecommendationProblem(Authentication authentication) {
        return recommendationService.refreshRecommendation(authentication);
    }

}
