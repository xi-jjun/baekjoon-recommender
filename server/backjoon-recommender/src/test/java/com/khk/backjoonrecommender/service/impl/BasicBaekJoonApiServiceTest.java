package com.khk.backjoonrecommender.service.impl;

import com.khk.backjoonrecommender.service.BaekJoonApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@SpringBootTest
class BasicBaekJoonApiServiceTest {
	@Autowired
	private BaekJoonApiService basicBaekJoonApiServiceTest;

	@Test
	void getProblemIdListFromBaekJoon() throws IOException {
		List<Long> problemIdList = basicBaekJoonApiServiceTest.getAllProblemIdListFromBaekJoon();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (Long problemId : problemIdList) {
			bw.write(problemId + "\n");
		}

		bw.flush();
		bw.close();
	}

	@Test
	void getUserSolvedProblemIdList() throws IOException {
		final String baekJoonId = "rlawowns000";
		List<Long> problemIdList = basicBaekJoonApiServiceTest.getSolvedProblemIdListByBaekJoonId(baekJoonId);
		System.out.println("total solved problem count = " + problemIdList.size());
		for (Long id : problemIdList) {
			System.out.println("id = " + id);
		}
	}
}