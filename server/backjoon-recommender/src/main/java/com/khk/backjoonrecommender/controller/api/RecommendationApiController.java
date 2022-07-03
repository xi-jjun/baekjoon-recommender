package com.khk.backjoonrecommender.controller.api;

import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/recommendation")
@RestController
public class RecommendationApiController {

    private final RecommendationService recommendationService;

    @GetMapping
    public BasicResponseDto<?> problemDetails() {
        return recommendationService.recommendProblem();
    }

    @PostMapping
    public BasicResponseDto<?> problemCheck() {
        return recommendationService.checkProblemIfSolved();
    }

    @GetMapping("/additional")
    public BasicResponseDto<?> additionalProblemDetails() {
        return recommendationService.findAdditionalProblem();
    }

    @GetMapping("/reload")
    public BasicResponseDto<?> problemReload() {
        return recommendationService.reloadProblem();
    }

}
