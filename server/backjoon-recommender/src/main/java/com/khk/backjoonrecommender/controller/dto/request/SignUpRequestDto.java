package com.khk.backjoonrecommender.controller.dto.request;

import lombok.Data;

@Data
public class SignUpRequestDto {
	private UserRequestDto userRequestDto;
	private SettingRequestDto settingRequestDto;
}
