package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.request.SignUpRequestDTO;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.MyPageResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.RivalListResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.RivalResponseDto;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.List;

public interface UserService {
	BasicResponseDto<MyPageResponseDto> findUser(Authentication authentication);

	BasicResponseDto<?> registerUser(SignUpRequestDTO signUpRequestDTO) throws IOException;

	BasicResponseDto<?> modifyUser();

	BasicResponseDto<List<RivalListResponseDto>> findRivals(Authentication authentication);

	BasicResponseDto<RivalResponseDto> addRival(Long rivalId);

	BasicResponseDto<?> deleteRival(Long rivalId);
}
