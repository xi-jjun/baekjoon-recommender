package com.khk.backjoonrecommender.controller.dto.request;

import com.khk.backjoonrecommender.entity.Option;
import com.khk.backjoonrecommender.entity.Setting;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

	@Builder
	public SettingRequestDTO(Option option, String levels, String tags, String sun, String mon,
							 String tue, String wed, String thu, String fri, String sat) {
		this.option = option;
		this.levels = levels;
		this.tags = tags;
		this.sun = sun;
		this.mon = mon;
		this.tue = tue;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
		this.sat = sat;
	}

	public Setting toEntity() {
		return Setting.builder()
				.option(option)
				.levels(levels)
				.tags(tags)
				.sun(sun).mon(mon).tue(tue).wed(wed).thu(thu).fri(fri).sat(sat)
				.build();
	}
}
