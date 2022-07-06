package com.khk.backjoonrecommender.controller.dto.request;

import com.khk.backjoonrecommender.entity.User;
import lombok.Data;

@Data
public class UserRequestDTO {
	private String username;
	private String baekJoonId;
	private String password;

	public User toEntity() {
		return User.builder()
				.username(username)
				.baekJoonId(baekJoonId)
				.password(password)
				.build();
	}
}
