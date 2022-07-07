package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public interface SystemService {
	public BasicResponseDto<?> migrateBaekJoonProblems(Authentication authentication) throws IOException, ParseException;

	public BasicResponseDto<?> resetUsersReloadCount();

	public BasicResponseDto<?> resetUserReloadCount(Long userId);
}
