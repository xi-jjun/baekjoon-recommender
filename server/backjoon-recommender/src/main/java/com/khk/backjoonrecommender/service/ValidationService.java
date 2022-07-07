package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.request.UserRequestDTO;

import java.io.IOException;

public interface ValidationService {
	public boolean validateBaekJoonId(String baekJoonId) throws IOException;

	public boolean validateUserRequestInfo(UserRequestDTO userRequestDTO);
}
