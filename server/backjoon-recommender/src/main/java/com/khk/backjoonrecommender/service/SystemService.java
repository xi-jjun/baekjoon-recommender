package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.entity.ProblemTag;
import com.khk.backjoonrecommender.entity.Tag;
import com.khk.backjoonrecommender.entity.User;
import com.khk.backjoonrecommender.repository.ProblemRepository;
import com.khk.backjoonrecommender.repository.ProblemTagRepository;
import com.khk.backjoonrecommender.repository.TagRepository;
import com.khk.backjoonrecommender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SystemService {
	private final BaekJoonApiService baekJoonApiService;
	private final ProblemRepository problemRepository;
	private final UserRepository userRepository;
	private final JSONParsingService jsonParsingService;
	private final TagRepository tagRepository;
	private final ProblemTagRepository problemTagRepository;

	private Set<Long> existedProblemIdList = new HashSet<>();

	@Transactional
	public BasicResponseDto<?> migrateBaekJoonProblems() throws IOException, ParseException {
		List<Long> totalProblemIdList = baekJoonApiService.getAllProblemIdListFromBaekJoon();
		List<Problem> totalProblemListFromDatabase = problemRepository.findAll();
		existedProblemIdList = getExistedProblemIdList(totalProblemListFromDatabase);

		int limit = 100;
		for (int i = 0; i < totalProblemIdList.size(); i += limit) {
			List<Long> problemIds = getProblemIdsByLimit(totalProblemIdList, i, limit);
			JSONArray problemsInfo = baekJoonApiService.getBulkProblemsInfoFromSolvedApi(problemIds); // 100 개의 문제 정보

			migrateProblems(problemsInfo); // 100개의 문제 정보를 저장
		}

		return new BasicResponseDto<>(200, "success to migrate problem list", totalProblemIdList.size());
	}

	private Set<Long> getExistedProblemIdList(List<Problem> totalProblemListFromDatabase) {
		return totalProblemListFromDatabase.stream()
				.map(Problem::getId)
				.collect(Collectors.toSet());
	}

	private void migrateProblems(JSONArray problemsInfo) {
		for (Object problemJsonInfo : problemsInfo) {
			JSONObject problemInfo = (JSONObject) problemJsonInfo;
			migrate(problemInfo);
		}
	}

	private void migrate(JSONObject problemInfo) {
		Problem problem = jsonParsingService.parseJSONObjectToProblem(problemInfo);

		if (!existedProblemIdList.contains(problem.getId())) {
			problemRepository.save(problem);
			log.info("problem {} is added", problem.getId());

			JSONArray tagsJsonArray = jsonParsingService.getJSONTagArrayFromJSONObject(problemInfo);
			List<String> tags = jsonParsingService.parseJSONArrayToTags(tagsJsonArray);

			saveAllTagsAndProblemTags(problem, tags);
		}
	}

	private void saveAllTagsAndProblemTags(Problem problem, List<String> tags) {
		for (String tagName : tags) {
			Optional<Tag> tag = tagRepository.findByTagName(tagName);
			if (tag.isPresent()) {
				saveProblemTagToDatabase(problem, tag.get());
				return;
			}
			Tag savedTag = saveTagToDatabase(tagName);
			saveProblemTagToDatabase(problem, savedTag);
		}
	}

	private List<Long> getProblemIdsByLimit(List<Long> totalProblemIdList, int start, int limit) {
		int end = Math.min(start + limit, totalProblemIdList.size());
		return totalProblemIdList.subList(start, end);
	}

	private Tag saveTagToDatabase(String tagName) {
		Tag tag = Tag.builder()
				.tagName(tagName)
				.build();
		tagRepository.save(tag);
		return tag;
	}

	private void saveProblemTagToDatabase(Problem problem, Tag tag) {
		ProblemTag problemTag = ProblemTag.builder()
				.problem(problem)
				.tag(tag)
				.build();
		problemTagRepository.save(problemTag);
	}

	@Transactional
	public BasicResponseDto<?> resetUserRefreshCount(Long userId) {
		Optional<User> findUser = userRepository.findById(userId);
		if (findUser.isPresent()) {
			User user = findUser.get();
			user.resetRefreshCount();

			BasicResponseDto<?> responseDto = new BasicResponseDto<>();
			responseDto.setCode(200);
			responseDto.setMessage("success to reset reload count user id=" + userId);

			return responseDto;
		}

		BasicResponseDto<?> responseDto = new BasicResponseDto<>();
		responseDto.setMessage("fail to reset reload count user id" + userId);
		responseDto.setCode(400);

		return responseDto;
	}

	@Transactional
	public void resetDailyRefreshCount() {
		List<User> users = userRepository.findAll();
		users.forEach(User::resetRefreshCount);
	}
}

