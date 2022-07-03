package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.entity.Problem;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@SpringBootTest
class BaekJoonProblemBasicCollectorTest {
	@Autowired
	private BaekJoonProblemCollector baekJoonProblemCollector;

	@Test
	void getBaekJoonProblemList() throws IOException, ParseException, InterruptedException {
		List<Problem> problemIdList = baekJoonProblemCollector.getAllProblemList();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (Problem problem : problemIdList) {
			bw.write("problem id, title" + problem.getId() + ", " + problem.getTitle());
			bw.write("you can solve here => " + problem.getProblemUrl());
		}
		bw.write("total problem count = " + problemIdList.size() + "\n");

		bw.flush();
		bw.close();
	}

	@Test
	void insertBaekJoonProblemListToServerDB() throws IOException, ParseException, InterruptedException {
		baekJoonProblemCollector.updateProblemList();
	}
}