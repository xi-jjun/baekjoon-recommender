package com.khk.backjoonrecommender.controller.api;

import com.khk.backjoonrecommender.controller.dto.request.SignUpRequestDTO;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.MyPageResponseDto;
import com.khk.backjoonrecommender.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserApiController {

	private final UserService userService;

	@GetMapping
	public BasicResponseDto<MyPageResponseDto> userDetails(Authentication authentication) {
		return userService.findUser(authentication);
	}

	@PostMapping
	public BasicResponseDto<?> userRegister(@RequestBody SignUpRequestDTO signUpRequestDTO) throws IOException {
		return userService.registerUser(signUpRequestDTO);
	}

	@PatchMapping
	public BasicResponseDto<?> userModify() {
		return userService.modifyUser();
	}
}
