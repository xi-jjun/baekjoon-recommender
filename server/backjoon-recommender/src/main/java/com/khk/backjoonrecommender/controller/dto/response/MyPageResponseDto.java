package com.khk.backjoonrecommender.controller.dto.response;

import com.khk.backjoonrecommender.entity.Option;
import com.khk.backjoonrecommender.entity.Setting;
import com.khk.backjoonrecommender.entity.User;
import lombok.Data;

@Data
public class MyPageResponseDto {

	private Long userId;
	private String username;
	private String baekJoonId;
	private int reloadCount;
	private Option option;
	private String levels;
	private String dailyTags;

	public MyPageResponseDto(User user, Setting setting) {
		userId = user.getId();
		username = user.getUsername();
		baekJoonId = user.getBaekJoonId();
		reloadCount = user.getRefreshCount();
		option = setting.getOption();
		levels = setting.getLevels();
		dailyTags = setting.getTags();
		option = setting.getOption();
	}
}
