package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.entity.Problem;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface BaekJoonApiService {
	public List<Long> getAllProblemIdListFromBaekJoon() throws IOException;

	public Problem getProblemByProblemId(Long problemId) throws IOException, ParseException;

	public List<Long> getSolvedProblemIdListByBaekJoonId(String baekJoonId) throws IOException;
}
