package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.MyPageResponseDto;

public interface UserService {
	public BasicResponseDto<MyPageResponseDto> findUser();

	public BasicResponseDto<?> registerUser();

	public BasicResponseDto<?> modifyUser();
}
