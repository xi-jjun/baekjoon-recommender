package com.khk.backjoonrecommender.controller.dto.request;

import lombok.Data;

@Data
public class SignUpRequestDTO {
	private UserRequestDTO userRequestDTO;
	private SettingRequestDTO settingRequestDTO;
}
