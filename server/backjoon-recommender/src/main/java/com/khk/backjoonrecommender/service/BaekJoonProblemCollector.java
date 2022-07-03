package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.entity.Problem;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface BaekJoonProblemCollector {
	public List<Problem> getAllProblemList() throws IOException, ParseException, InterruptedException;

	public Problem getProblemInfoByProblemId(Long problemId) throws IOException, ParseException;

	public void updateProblemList() throws IOException, ParseException, InterruptedException;
}
