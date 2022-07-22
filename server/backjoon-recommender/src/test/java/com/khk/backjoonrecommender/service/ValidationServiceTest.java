package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ValidationServiceTest {
	@Autowired
	private ValidationService validationService;

	@Test
	void checkResponseCode() throws IOException {
		final String EXISTED_BAEK_JOON_ID = "rlawowns000";
		boolean isExisted = validationService.validateBaekJoonId(EXISTED_BAEK_JOON_ID);

		assertThat(isExisted).isTrue();
	}

	@Test
	void checkResponseNotExistedBaekJoonId() throws IOException {
		final String NOT_EXISTED_BAEK_JOON_ID = "kaf93nk3j4";
		boolean isExisted = validationService.validateBaekJoonId(NOT_EXISTED_BAEK_JOON_ID);

		assertThat(isExisted).isFalse();
	}
}