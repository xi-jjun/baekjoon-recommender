package com.khk.backjoonrecommender.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BaekJoonCrawlingTest {
	private BaekJoonCrawling baekJoonCrawling = new BaekJoonCrawling();

	@Test
	void getHtml() throws IOException {
		baekJoonCrawling.getProblem();
	}
}