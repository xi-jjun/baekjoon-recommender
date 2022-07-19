package com.khk.backjoonrecommender.controller.api;

import com.khk.backjoonrecommender.controller.dto.request.RivalSearchRequestDto;
import com.khk.backjoonrecommender.controller.dto.request.UserRegisterRequestDto;
import com.khk.backjoonrecommender.controller.dto.response.*;
import com.khk.backjoonrecommender.service.UserService;
import com.khk.backjoonrecommender.service.impl.RivalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserApiController {

	private final UserService userService;
	private final RivalService rivalService;

	@GetMapping
	public BasicResponseDto<MyPageResponseDto> userDetails(Authentication authentication) {
		return userService.findUser(authentication);
	}

	@GetMapping("/{userId}/solved")
	public BasicResponseDto<List<SolvedProblemListResponseDto>> solvedProblemList(@PathVariable Long userId) {
		return userService.getSolvedProblemList(userId);
	}

	@GetMapping("/rivals")
	public BasicResponseDto<List<RivalListResponseDto>> userRivals(Authentication authentication) {
		return rivalService.findRivals(authentication);
	}

	@PostMapping("/rivals")
	public BasicResponseDto<RivalSearchResponseDto> rivalSearch(@RequestBody @Validated RivalSearchRequestDto rivalSearchDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("유저명을 입력해주세요.");
		}
		return rivalService.findRival(rivalSearchDto);
	}

	@PostMapping("/rivals/{rivalId}")
	public BasicResponseDto<RivalResponseDto> rivalAdd(@PathVariable Long rivalId) {
		return rivalService.addRival(rivalId);
	}

	@DeleteMapping("/rivals/{rivalId}")
	public BasicResponseDto<?> rivalDelete(@PathVariable Long rivalId) {
		return rivalService.deleteRival(rivalId);
	}

	@PostMapping
	public BasicResponseDto<?> registerUser(@RequestBody @Validated UserRegisterRequestDto userRegisterRequestDto, BindingResult bindingResult) throws IOException {
		return userService.registerUser(userRegisterRequestDto, bindingResult);
	}

	@PatchMapping
	public BasicResponseDto<?> modifyUser(Authentication authentication, @RequestBody @Validated UserRegisterRequestDto userRegisterRequestDto, BindingResult bindingResult) {
		return userService.modifyUser(authentication, userRegisterRequestDto, bindingResult);
	}

	@DeleteMapping
	public BasicResponseDto<?> deleteUser(Authentication authentication) {
		return userService.deleteUserInfo(authentication);
	}
}
