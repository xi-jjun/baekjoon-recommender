package com.khk.backjoonrecommender.service.impl;

import com.khk.backjoonrecommender.controller.dto.request.UserRequestDto;
import com.khk.backjoonrecommender.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Service
public class BasicValidationService implements ValidationService {
	private static final String USER_DETAIL_INFO_URL = "https://acmicpc.net/user/";

	@Override
	public boolean validateBaekJoonId(String baekJoonId) throws IOException {
		URL userCheckingUrl = new URL(USER_DETAIL_INFO_URL + baekJoonId);
		HttpURLConnection connection = (HttpURLConnection) userCheckingUrl.openConnection();
		int responseCode = connection.getResponseCode();
		
		if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
			log.info("{} is not exited", baekJoonId);
			return false;
		}

		return true;
	}
}
