package com.khk.backjoonrecommender.controller.dto.request;

import com.khk.backjoonrecommender.entity.Option;
import com.khk.backjoonrecommender.entity.Setting;
import lombok.Data;

@Data
public class SettingRequestDTO {
	private Option option;
	private String levels;
	private String tags;
	private String sun;
	private String mon;
	private String tue;
	private String wed;
	private String thu;
	private String fri;
	private String sat;

	public Setting toEntity() {
		return Setting.builder()
				.option(option)
				.levels(levels)
				.tags(tags)
				.sun(sun).mon(mon).tue(tue).wed(wed).thu(thu).fri(fri).sat(sat)
				.build();
	}
}
