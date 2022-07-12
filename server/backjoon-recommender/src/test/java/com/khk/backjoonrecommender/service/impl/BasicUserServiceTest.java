package com.khk.backjoonrecommender.service.impl;

import com.khk.backjoonrecommender.controller.dto.request.UserRegisterRequestDto;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.entity.Option;
import com.khk.backjoonrecommender.exception.BaekJoonIdNotFoundException;
import com.khk.backjoonrecommender.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@SpringBootTest
class BasicUserServiceTest {
	@Autowired
	private UserService userService;

	@Test
	void signUpValidBaekJoonId() throws IOException {
		// given : 사용자로부터 2개의 dto 객체 정보를 받는다. 사용자 정보와 추천 필터 정보
		// 사용자 가입 정보
		final String EXISTED_BAEK_JOON_ID = "rlawowns000";

		// 사용자가 TODAY 옵션으로 추천필터를 설정했다는 시나리오
		final Option option = Option.TODAY;
		UserRegisterRequestDto userRegisterRequestDto = getUserRegisterRequestDto(EXISTED_BAEK_JOON_ID, option);

		BasicResponseDto<?> response = userService.registerUser(userRegisterRequestDto);
		final int SUCCESS_CODE = 200;

		assertThat(response.getCode()).isEqualTo(SUCCESS_CODE);
	}

	@Test
	void signUpInvalidBaekJoonId() throws IOException {
		// given : 사용자로부터 2개의 dto 객체 정보를 받는다. 사용자 정보와 추천 필터 정보
		// 사용자 가입 정보
		final String NOT_EXISTED_BAEK_JOON_ID = "kaf93nk3j4";

		// 사용자가 TODAY 옵션으로 추천필터를 설정했다는 시나리오
		final Option option = Option.TODAY;
		UserRegisterRequestDto userRegisterRequestDto = getUserRegisterRequestDto(NOT_EXISTED_BAEK_JOON_ID, option);

		assertThatThrownBy(() -> userService.registerUser(userRegisterRequestDto))
				.isInstanceOf(BaekJoonIdNotFoundException.class);
	}

	private UserRegisterRequestDto getUserRegisterRequestDto(String EXISTED_BAEK_JOON_ID, Option option) {
		UserRegisterRequestDto userRegisterRequestDto = new UserRegisterRequestDto();
		userRegisterRequestDto.setUsername("jj");
		userRegisterRequestDto.setPassword("123");
		userRegisterRequestDto.setBaekJoonId(EXISTED_BAEK_JOON_ID);
		userRegisterRequestDto.setOption(option);
		userRegisterRequestDto.setLevels("1,2,3,4,5");
		userRegisterRequestDto.setTags("DP,DFS,BFS");
		return userRegisterRequestDto;
	}

}