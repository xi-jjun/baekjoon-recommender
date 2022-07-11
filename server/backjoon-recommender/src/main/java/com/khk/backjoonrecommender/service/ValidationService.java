package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.request.UserRequestDTO;
import com.khk.backjoonrecommender.entity.User;

import java.io.IOException;

public interface ValidationService {
	boolean validateBaekJoonId(String baekJoonId) throws IOException;

	boolean validateUserRequestInfo(UserRequestDTO userRequestDTO);
}
