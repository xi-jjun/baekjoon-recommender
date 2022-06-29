package com.khk.backjoonrecommender.controller.api;

import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.MyPageResponseDto;
import com.khk.backjoonrecommender.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserApiController {

    private final UserService userService;

    @GetMapping
    public BasicResponseDto<MyPageResponseDto> userDetails() {
        return userService.findUser();
    }

    @PostMapping
    public BasicResponseDto<?> userRegister() {
        return userService.registerUser();
    }

    @PatchMapping
    public BasicResponseDto<?> userModify() {
        return userService.modifyUser();
    }
}
