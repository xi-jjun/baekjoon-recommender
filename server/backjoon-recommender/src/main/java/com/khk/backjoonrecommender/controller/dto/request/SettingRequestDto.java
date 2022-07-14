package com.khk.backjoonrecommender.controller.dto.request;

import com.khk.backjoonrecommender.entity.Option;
import com.khk.backjoonrecommender.entity.Setting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettingRequestDto {
	@NotNull
	private Option option;
	@NotBlank
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
