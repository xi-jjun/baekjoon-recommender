package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.entity.Problem;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class JSONParsingService {
	private static final String PROBLEM_ID = "problemId";
	private static final String PROBLEM_TITLE = "titleKo";
	private static final String PROBLEM_LEVEL = "level";
	private static final String PROBLEM_TAGS = "tags";
	private static final String PROBLEM_TAG = "key";

	public Problem parseJSONObjectToProblem(JSONObject jsonProblemObj) {
		long problemId = getProblemIdFromJSONObject(jsonProblemObj);
		String title = getTitleFromJSONObject(jsonProblemObj);
		int level = getLevelFromJSONObject(jsonProblemObj);

		return Problem.builder()
				.id(problemId)
				.title(title)
				.level(level)
				.build();
	}

	public List<String> parseJSONArrayToTags(JSONArray tagsJsonInfo) {
		List<String> tags = new ArrayList<>();
		for (Object tagJsonInfo : tagsJsonInfo) {
			String tag = getTagFromObject((JSONObject) tagJsonInfo);
			tags.add(tag);
		}

		return tags;
	}

	public JSONArray stringToJSONArray(String string) throws ParseException {
		JSONParser parser = new JSONParser();
		return (JSONArray) parser.parse(String.valueOf(string));
	}

	private String getTagFromObject(JSONObject tagJsonInfo) {
		return tagJsonInfo.get(PROBLEM_TAG).toString();
	}


	public JSONArray getJSONTagArrayFromJSONObject(JSONObject jsonObject) {
		return (JSONArray) jsonObject.get(PROBLEM_TAGS);
	}

	public String getTitleFromJSONObject(JSONObject jsonObject) {
		return jsonObject.get(PROBLEM_TITLE).toString();
	}

	public int getLevelFromJSONObject(JSONObject jsonObject) {
		return Integer.parseInt(jsonObject.get(PROBLEM_LEVEL).toString());
	}

	public long getProblemIdFromJSONObject(JSONObject jsonObject) {
		return Long.parseLong(jsonObject.get(PROBLEM_ID).toString());
	}
}
