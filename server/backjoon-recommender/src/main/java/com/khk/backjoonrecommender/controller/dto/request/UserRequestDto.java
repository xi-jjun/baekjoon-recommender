package com.khk.backjoonrecommender.controller.dto.request;

import com.khk.backjoonrecommender.entity.Role;
import com.khk.backjoonrecommender.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserRequestDTO {

	@Size(min = 4, max = 20, message = "4 ~ 20자 범위 내로 입력해주세요.")
	private String username;
	private String baekJoonId;

	@Size(min = 8, max = 20, message = "8 ~ 20자 범위 내로 입랙해주세요.")
	private String password;

	@Builder
	public UserRequestDTO(String username, String baekJoonId, String password) {
		this.username = username;
		this.baekJoonId = baekJoonId;
		this.password = password;
	}

	public User toEntity() {
		return User.builder()
				.username(username)
				.baekJoonId(baekJoonId)
				.password(password)
				.role(Role.USER)
				.build();
	}
}
