package com.khk.backjoonrecommender.library;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class JsonParserTest {
	@Test
	void jsonParserTest() {
		String jsonStr = "{\"problemId\":1000,\"titleKo\":\"A+B\",\"titles\":[{\"language\":\"en\",\"languageDisplayName\":\"en\",\"title\":\"A+B\",\"isOriginal\":false},{\"language\":\"ko\",\"languageDisplayName\":\"ko\",\"title\":\"A+B\",\"isOriginal\":true}],\"isSolvable\":true,\"isPartial\":false,\"acceptedUserCount\":186281,\"level\":1,\"votedUserCount\":17,\"sprout\":true,\"givesNoRating\":false,\"isLevelLocked\":true,\"averageTries\":2.383,\"official\":true,\"tags\":[{\"key\":\"implementation\",\"isMeta\":false,\"bojTagId\":102,\"problemCount\":3708,\"displayNames\":[{\"language\":\"ko\",\"name\":\"구현\",\"short\":\"구현\"},{\"language\":\"en\",\"name\":\"implementation\",\"short\":\"impl\"}]},{\"key\":\"arithmetic\",\"isMeta\":false,\"bojTagId\":121,\"problemCount\":625,\"displayNames\":[{\"language\":\"ko\",\"name\":\"사칙연산\",\"short\":\"사칙연산\"},{\"language\":\"en\",\"name\":\"arithmetic\",\"short\":\"arithmetic\"}]},{\"key\":\"math\",\"isMeta\":false,\"bojTagId\":124,\"problemCount\":4180,\"displayNames\":[{\"language\":\"ko\",\"name\":\"수학\",\"short\":\"수학\"},{\"language\":\"en\",\"name\":\"mathematics\",\"short\":\"math\"}]}]}";

		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonStr);

			Object problemId = jsonObject.get("problemId");
			String string = problemId.toString();
			Long id = Long.parseLong(string);
			System.out.println("id = " + id);

			String title = jsonObject.get("titleKo").toString();
			System.out.println("title = " + title);

			String levelInfo = jsonObject.get("level").toString();
			int level = Integer.parseInt(levelInfo);
			System.out.println("level = " + level);

			JSONArray tags = (JSONArray) jsonObject.get("tags");
			for (Object element : tags) {
				JSONObject tag = (JSONObject) element;
				String problemType = tag.get("key").toString();
				System.out.println("problemType = " + problemType);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
