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
	private String sunTags;
	private String monTags;
	private String tueTags;
	private String wedTags;
	private String thuTags;
	private String friTags;
	private String satTags;

	public MyPageResponseDto(User user, Setting setting) {
		userId = user.getId();
		username = user.getUsername();
		baekJoonId = user.getBaekJoonId();
		reloadCount = user.getReloadCount();
		option = setting.getOption();
		levels = setting.getLevels();
		dailyTags = setting.getTags();
		sunTags = setting.getSun();
		monTags = setting.getMon();
		tueTags = setting.getTue();
		wedTags = setting.getWed();
		thuTags = setting.getThu();
		friTags = setting.getFri();
		satTags = setting.getSat();
		option = setting.getOption();
	}
}
