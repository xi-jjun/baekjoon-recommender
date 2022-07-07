package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.request.SignUpRequestDTO;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.MyPageResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.RivalListResponseDto;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.List;

public interface UserService {
	public BasicResponseDto<MyPageResponseDto> findUser(Authentication authentication);

	public BasicResponseDto<?> registerUser(SignUpRequestDTO signUpRequestDTO) throws IOException;

	public BasicResponseDto<?> modifyUser();

	BasicResponseDto<List<RivalListResponseDto>> findRivals(Authentication authentication);
}
