package com.khk.backjoonrecommender.service.impl;

import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.repository.ProblemRepository;
import com.khk.backjoonrecommender.service.BaekJoonProblemCollector;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BaekJoonProblemBasicCollector implements BaekJoonProblemCollector {
	private final ProblemRepository problemRepository;
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
	public List<Problem> getAllProblemList() throws IOException, ParseException, InterruptedException {
		List<Problem> problemList = new ArrayList<>();
		int pageNumber = 1;
		Document document = null;
		Element hasNext = null;
		do {
			document = Jsoup.connect(BASIC_BAEKJOON_PROBLEM_LIST_URL + pageNumber).get();
			Elements problemIdElements = document.getElementsByClass(PROBLEM_LIST_CLASS);

			for (Element problemIdElement : problemIdElements) {
				String problemIdText = problemIdElement.html();
				Long problemId = Long.parseLong(problemIdText);

				Problem problem = getProblemInfoByProblemId(problemId);
				log.info("problem Id={}, title={}", problem.getId(), problem.getTitle());

				problemList.add(problem);
				Thread.sleep(400); // 1 초까지는 괜찮아 보였음. 그러나 너무 느려서 중간에 테스트 중단
			}

			hasNext = document.getElementById(NEXT_PAGE_ID);

			++pageNumber;
		} while (hasNext != null);

		return problemList;
	}

	@Override
	public Problem getProblemInfoByProblemId(Long problemId) throws IOException, ParseException {
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
	public void updateProblemList() throws IOException, ParseException, InterruptedException {
		List<Problem> remainedProblemList = problemRepository.findAll();
		Set<Long> existed = new HashSet<>();
		for (Problem problem : remainedProblemList) {
			existed.add(problem.getId());
		}

		List<Problem> baekJoonProblemList = getAllProblemList();
		for (Problem problem : baekJoonProblemList) {
			Long id = problem.getId();
			if (!existed.contains(id)) {
				problemRepository.save(problem);
				log.info("{} is inserted in database", id);
			}
		}
	}

	@Override
	public List<Long> getProblemIdListByBaekJoonId(String baekJoonId) throws IOException {
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
