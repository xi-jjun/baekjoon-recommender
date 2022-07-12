package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.request.SignUpRequestDTO;
import com.khk.backjoonrecommender.controller.dto.request.UserRegisterRequestDto;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.MyPageResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.RivalListResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.RivalResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.List;

public interface UserService {
	BasicResponseDto<MyPageResponseDto> findUser(Authentication authentication);

	BasicResponseDto<?> registerUser(UserRegisterRequestDto userRegisterRequestDto) throws IOException;
	BasicResponseDto<?> registerUser(UserRegisterRequestDto userRegisterRequestDto, BindingResult bindingResult) throws IOException;

	BasicResponseDto<?> modifyUser();

	BasicResponseDto<List<RivalListResponseDto>> findRivals(Authentication authentication);

	BasicResponseDto<RivalResponseDto> addRival(Long rivalId);

	BasicResponseDto<?> deleteRival(Long rivalId);
}
