package com.khk.backjoonrecommender.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BaekJoonApiServiceTest {
	private JSONParsingService jsonParsingService = new JSONParsingService();
	private BaekJoonApiService baekJoonApiService = new BaekJoonApiService(jsonParsingService);

	@Test
	void getProblemIdListFromBaekJoon() throws IOException {
		List<Long> problemIdList = baekJoonApiService.getAllProblemIdListFromBaekJoon();
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
		List<Long> problemIdList = baekJoonApiService.getSolvedProblemIdListByBaekJoonId(baekJoonId);
		System.out.println("total solved problem count = " + problemIdList.size());
		for (Long id : problemIdList) {
			System.out.println("id = " + id);
		}
	}

	@Test
	void getBulkProblemListInfo() throws IOException, ParseException {
		final String[] problemIds = {"1000", "1001", "1002"};
		List<Long> ids = Arrays.asList(1000L, 1001L, 1002L);
		JSONArray result = baekJoonApiService.getBulkProblemsInfoFromSolvedApi(ids);
		int idx = 0;
		for (Object obj : result) {
			JSONObject object = (JSONObject) obj;
			String problemId = object.get("problemId").toString();
			String level = object.get("level").toString();

			assertThat(problemId).isEqualTo(problemIds[idx++]);
			System.out.println("problemId = " + problemId);
		}

	}
}