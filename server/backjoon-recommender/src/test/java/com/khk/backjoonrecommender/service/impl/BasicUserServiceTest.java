package com.khk.backjoonrecommender.service.impl;

import com.khk.backjoonrecommender.controller.dto.request.SettingRequestDTO;
import com.khk.backjoonrecommender.controller.dto.request.SignUpRequestDTO;
import com.khk.backjoonrecommender.controller.dto.request.UserRequestDTO;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.entity.Option;
import com.khk.backjoonrecommender.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

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
		UserRequestDTO userRequestDTO = getUserRequestDTO(EXISTED_BAEK_JOON_ID);

		// 사용자가 TODAY 옵션으로 추천필터를 설정했다는 시나리오
		final Option option = Option.TODAY;
		SettingRequestDTO settingRequestDTO = getSettingRequestDTO(option);

		// 위 2개의 dto 를 @RequestBody 로 한번에 받기 위해서는 아래와 같은 Wrapper class 가 필요하다.
		SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
		signUpRequestDTO.setUserRequestDTO(userRequestDTO);
		signUpRequestDTO.setSettingRequestDTO(settingRequestDTO);


		// when : controller 로 부터 넘어온 wrapper class signUpRequestDTO 를 service layer 에 넘겨주게 되면
		// 		  정상적으로 각각의 dto 객체들이 나눠지는지 확인하기 위한 코드이다.
		BasicResponseDto<?> response = userService.registerUser(signUpRequestDTO);
		final int SUCCESS_CODE = 200;

		assertThat(response.getCode()).isEqualTo(SUCCESS_CODE);
	}

	@Test
	void signUpInvalidBaekJoonId() throws IOException {
		// given : 사용자로부터 2개의 dto 객체 정보를 받는다. 사용자 정보와 추천 필터 정보
		// 사용자 가입 정보
		final String NOT_EXISTED_BAEK_JOON_ID = "kaf93nk3j4";
		UserRequestDTO userRequestDTO = getUserRequestDTO(NOT_EXISTED_BAEK_JOON_ID);

		// 사용자가 TODAY 옵션으로 추천필터를 설정했다는 시나리오
		final Option option = Option.TODAY;
		SettingRequestDTO settingRequestDTO = getSettingRequestDTO(option);

		// 위 2개의 dto 를 @RequestBody 로 한번에 받기 위해서는 아래와 같은 Wrapper class 가 필요하다.
		SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
		signUpRequestDTO.setUserRequestDTO(userRequestDTO);
		signUpRequestDTO.setSettingRequestDTO(settingRequestDTO);


		// when : controller 로 부터 넘어온 wrapper class signUpRequestDTO 를 service layer 에 넘겨주게 되면
		// 		  정상적으로 각각의 dto 객체들이 나눠지는지 확인하기 위한 코드이다.
		BasicResponseDto<?> response = userService.registerUser(signUpRequestDTO);
		final int FAIL_CODE = 400;

		assertThat(response.getCode()).isEqualTo(FAIL_CODE);
	}

	private SettingRequestDTO getSettingRequestDTO(Option option) {
		SettingRequestDTO settingRequestDTO = new SettingRequestDTO();
		settingRequestDTO.setOption(option);
		settingRequestDTO.setLevels("1,2,3,4,5");
		settingRequestDTO.setTags("DP,DFS,BFS");
		return settingRequestDTO;
	}

	private UserRequestDTO getUserRequestDTO(String EXISTED_BAEK_JOON_ID) {
		UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setUsername("jj");
		userRequestDTO.setPassword("123");
		userRequestDTO.setBaekJoonId(EXISTED_BAEK_JOON_ID);
		return userRequestDTO;
	}

}