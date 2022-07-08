package com.khk.backjoonrecommender.service.impl;

import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.service.BaekJoonApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BasicBaekJoonApiService implements BaekJoonApiService {
	private static final String BASIC_BAEKJOON_PROBLEM_LIST_URL = "https://www.acmicpc.net/problemset/";
	private static final String PROBLEM_LIST_CLASS = "list_problem_id";
	private static final String NEXT_PAGE_ID = "next_page";
	private static final String BASIC_DETAIL_PROBLEM_URL = "https://www.acmicpc.net/problem/";

	private static final String TITLE = "titleKo";
	private static final String LEVEL = "level";
	private static final String TAGS = "tags";
	private static final String TAG_KEY = "key";
	private static final String PROBLEM_INFO_API = "https://solved.ac/api/v3/problem/show?problemId=";

	private static final String USER_SOLVED_PROBLEM_LIST_URL = "https://www.acmicpc.net/user/";

	@Override
	public List<Long> getAllProblemIdListFromBaekJoon() throws IOException {
		List<Long> problemIdList = new ArrayList<>();
		int pageNumber = 1;
		Document document;
		Element hasNext;
		do {
			document = Jsoup.connect(BASIC_BAEKJOON_PROBLEM_LIST_URL + pageNumber).get();
			Elements thisPageProblemIdElements = document.getElementsByClass(PROBLEM_LIST_CLASS);
			for (Element problemIdElement : thisPageProblemIdElements) {
				Long problemId = Long.parseLong(problemIdElement.html());
				problemIdList.add(problemId);
			}
			hasNext = document.getElementById(NEXT_PAGE_ID);
			++pageNumber;
			if (pageNumber == 3) break; // 일단 2페이지(총 200문제) 분량만 테스트로 저장하기 했습니다
		} while (hasNext != null);

		return problemIdList;
	}

	@Override
	public Problem getProblemByProblemId(Long problemId) throws IOException, ParseException {
		JSONObject problemObj = getProblemInfoFromSolvedAPI(problemId);

		String title = problemObj.get(TITLE).toString();
		int level = Integer.parseInt(problemObj.get(LEVEL).toString());

		String tags = getTags(problemObj);

		Problem problem = Problem.builder()
				.id(problemId)
				.title(title)
				.level(level)
				.tags(tags)
				.problemUrl(BASIC_DETAIL_PROBLEM_URL + problemId)
				.build();

		return problem;
	}

	private String getTags(JSONObject problemObj) {
		List<String> tagArray = new ArrayList<>();
		JSONArray tagObjects = (JSONArray) problemObj.get(TAGS);
		for (Object tagObject : tagObjects) {
			JSONObject jsonTagObject = (JSONObject) tagObject;
			String problemType = jsonTagObject.get(TAG_KEY).toString();
			tagArray.add(problemType);
		}

		return String.join(",", tagArray);
	}

	@Override
	public List<Long> getSolvedProblemIdListByBaekJoonId(String baekJoonId) throws IOException {
		Document document = Jsoup.connect(USER_SOLVED_PROBLEM_LIST_URL + baekJoonId).get();
		Element solvedElements = document.getElementsByClass("problem-list").get(0);
		String[] solvedIdList = solvedElements.text().split(" ");

		return Arrays.stream(solvedIdList)
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}

	private JSONObject getProblemInfoFromSolvedAPI(Long problemId) throws IOException, ParseException {
		URL url = new URL(PROBLEM_INFO_API + problemId);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod("GET");
		int responseCode = connection.getResponseCode();

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String input;
		StringBuilder response = new StringBuilder();
		while ((input = reader.readLine()) != null) {
			response.append(input);
		}
		reader.close();

		return stringToJson(response.toString());
	}

	private JSONObject stringToJson(String response) throws ParseException {
		JSONParser parser = new JSONParser();
		return (JSONObject) parser.parse(response);
	}
}
