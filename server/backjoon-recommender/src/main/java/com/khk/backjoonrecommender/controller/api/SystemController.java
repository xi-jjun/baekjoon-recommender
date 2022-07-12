package com.khk.backjoonrecommender.controller.api;

import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.service.SystemService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api/v1/system")
@RestController
public class SystemController {
	private final SystemService systemService;

	@PatchMapping("/problem-list")
	public BasicResponseDto<?> updateLatestBaekJoonProblemsToServer(Authentication authentication) throws IOException, ParseException {
		return systemService.migrateBaekJoonProblems(authentication);
	}

	@PatchMapping("/reload-count")
	public BasicResponseDto<?> resetAllUsersReloadCount() {
		return systemService.resetUsersReloadCount();
	}

	@PatchMapping("/{userId}/reload-count")
	public BasicResponseDto<?> resetUserReloadCount(@PathVariable("userId") Long userId) {
		return systemService.resetUserReloadCount(userId);
	}
}
