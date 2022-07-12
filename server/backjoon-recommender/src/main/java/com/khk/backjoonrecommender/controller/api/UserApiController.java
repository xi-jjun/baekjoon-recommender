package com.khk.backjoonrecommender.controller.api;

import com.khk.backjoonrecommender.controller.dto.request.SignUpRequestDTO;
import com.khk.backjoonrecommender.controller.dto.request.UserRegisterRequestDto;
import com.khk.backjoonrecommender.controller.dto.request.UserRequestDTO;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.MyPageResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.RivalListResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.RivalResponseDto;
import com.khk.backjoonrecommender.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserApiController {

	private final Validator validator;
	private final UserService userService;

	@GetMapping
	public BasicResponseDto<MyPageResponseDto> userDetails(Authentication authentication) {
		return userService.findUser(authentication);
	}

	@GetMapping("/rivals")
	public BasicResponseDto<List<RivalListResponseDto>> userRivals(Authentication authentication) {
		return userService.findRivals(authentication);
	}

	@PostMapping("/rivals/{rivalId}")
	public BasicResponseDto<RivalResponseDto> rivalAdd(@PathVariable Long rivalId) {
		return userService.addRival(rivalId);
	}

	@DeleteMapping("/rivals/{rivalId}")
	public BasicResponseDto<?> rivalDelete(@PathVariable Long rivalId) {
		return userService.deleteRival(rivalId);
	}

	@PostMapping
	public BasicResponseDto<?> userRegister(@RequestBody @Validated UserRegisterRequestDto userRegisterRequestDto, BindingResult bindingResult) throws IOException {

		return userService.registerUser(userRegisterRequestDto, bindingResult);
	}

	@PatchMapping
	public BasicResponseDto<?> userModify() {
		return userService.modifyUser();
	}
}
