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
	@NotBlank
	private String tags;

	public Setting toEntity() {
		return Setting.builder()
				.option(option)
				.levels(levels)
				.tags(tags)
				.build();
	}
}
