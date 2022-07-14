package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.request.RivalSearchRequestDto;
import com.khk.backjoonrecommender.controller.dto.request.UserRegisterRequestDto;
import com.khk.backjoonrecommender.controller.dto.response.*;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.List;

public interface UserService {
	BasicResponseDto<MyPageResponseDto> findUser(Authentication authentication);

	BasicResponseDto<?> registerUser(UserRegisterRequestDto userRegisterRequestDto, BindingResult bindingResult) throws IOException;

	BasicResponseDto<?> modifyUser(Authentication authentication, UserRegisterRequestDto userRegisterRequestDto, BindingResult bindingResult);

	BasicResponseDto<List<RivalListResponseDto>> findRivals(Authentication authentication);

	BasicResponseDto<RivalResponseDto> addRival(Long rivalId);

	BasicResponseDto<?> deleteRival(Long rivalId);

	BasicResponseDto<RivalSearchResponseDto> findRival(RivalSearchRequestDto rivalSearchRequestDto);

	BasicResponseDto<List<SolvedProblemListResponseDto>> getSolvedProblemList(Long userId);

	BasicResponseDto<?> deleteUserInfo(Authentication authentication);
}
