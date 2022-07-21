package com.khk.backjoonrecommender.controller.api;

import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.service.SystemService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
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

	@PatchMapping("/latest-problems")
	public BasicResponseDto<?> updateLatestBaekJoonProblems() throws IOException, ParseException {
		return systemService.migrateBaekJoonProblems();
	}

	@PatchMapping("/refresh-count")
	public void resetAllUsersRefreshCount() {
		systemService.resetDailyRefreshCount();
	}

	@PatchMapping("/{userId}/refresh-count")
	public BasicResponseDto<?> resetUserRefreshCount(@PathVariable("userId") Long userId) {
		return systemService.resetUserRefreshCount(userId);
	}
}
