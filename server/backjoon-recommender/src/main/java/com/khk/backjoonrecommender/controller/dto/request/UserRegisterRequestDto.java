package com.khk.backjoonrecommender.controller.dto.request;

import com.khk.backjoonrecommender.entity.Option;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserRegisterRequestDto {

    @Size(min = 4, max = 20, message = "4 ~ 20자 범위 내로 입력해주세요.")
    private String username;

    @Size(min = 8, max = 20, message = "8 ~ 20자 범위 내로 입랙해주세요.")
    private String password;

    private String baekJoonId;

    private Option option;
    private String levels;
    private String tags;

    public UserRequestDto toUserDto() {
        return UserRequestDto.builder()
                .username(username)
                .baekJoonId(baekJoonId)
                .password(password)
                .build();
    }

    public SettingRequestDto toSettingDto() {
        return SettingRequestDto.builder()
                .option(option)
                .levels(levels)
                .tags(tags)
                .build();
    }
}

