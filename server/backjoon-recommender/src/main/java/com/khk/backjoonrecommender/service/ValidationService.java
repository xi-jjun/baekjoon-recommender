package com.khk.backjoonrecommender.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Service
public class ValidationService {
	private static final String USER_DETAIL_INFO_CHECK_URL = "https://acmicpc.net/user/";

	public boolean validateBaekJoonId(String baekJoonId) throws IOException {
		URL userCheckingUrl = new URL(USER_DETAIL_INFO_CHECK_URL + baekJoonId);
		HttpURLConnection connection = (HttpURLConnection) userCheckingUrl.openConnection();
		int responseCode = connection.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
			log.info("{} is not exited in baekjoon", baekJoonId);
			return false;
		}

		return true;
	}
}
