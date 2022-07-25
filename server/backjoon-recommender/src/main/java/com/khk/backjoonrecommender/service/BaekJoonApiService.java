package com.khk.backjoonrecommender.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
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
public class BaekJoonApiService {
	private final JSONParsingService jsonParsingService;

	private static final String BASIC_BAEKJOON_PROBLEM_LIST_URL = "https://www.acmicpc.net/problemset/";
	private static final String PROBLEM_LIST_CLASS = "list_problem_id";
	private static final String NEXT_PAGE_ID = "next_page";
	private static final String USER_SOLVED_PROBLEM_LIST_URL = "https://www.acmicpc.net/user/";
	private static final String BULK_PROBLEMS_INFO_API = "https://solved.ac/api/v3/problem/lookup?problemIds=";

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

		} while (hasNext != null);

		return problemIdList;
	}

	public List<Long> getSolvedProblemIdListByBaekJoonId(String baekJoonId) throws IOException {
		Document document = Jsoup.connect(USER_SOLVED_PROBLEM_LIST_URL + baekJoonId).get();
		Element solvedElements = document.getElementsByClass("problem-list").get(0);
		String solvedElementsText = solvedElements.text();
		if (solvedElementsText.isBlank()) {
			return List.of(0L);
		}

		String[] parsedSolvedIdList = solvedElementsText.split(" ");

		return Arrays.stream(parsedSolvedIdList)
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}

	public JSONArray getBulkProblemsInfoFromSolvedApi(List<Long> ids) throws IOException, ParseException {
		List<String> idList = longToStringList(ids);
		String problemIds = String.join(",", idList);
		URL url = new URL(BULK_PROBLEMS_INFO_API + problemIds);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		int responseCode = connection.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			StringBuilder response = getResponseFromUrlConnection(connection);
			JSONArray problemsJsonArrayInfo = jsonParsingService.stringToJSONArray(response.toString());
			return problemsJsonArrayInfo;
		}

		throw new IllegalStateException("solved api error"); // 예외처리 필요 error handler 에 추가 하고 싶다.
	}

	private StringBuilder getResponseFromUrlConnection(HttpURLConnection connection) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder response = new StringBuilder();
		String input;
		while ((input = reader.readLine()) != null) {
			response.append(input);
		}
		reader.close();

		return response;
	}

	private List<String> longToStringList(List<Long> list) {
		return list.stream()
				.map(String::valueOf)
				.collect(Collectors.toList());
	}
}
