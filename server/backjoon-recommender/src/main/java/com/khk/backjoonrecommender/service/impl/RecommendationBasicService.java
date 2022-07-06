package com.khk.backjoonrecommender.service.impl;

import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.entity.Option;
import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.entity.Setting;
import com.khk.backjoonrecommender.entity.User;
import com.khk.backjoonrecommender.repository.ProblemRepository;
import com.khk.backjoonrecommender.repository.UserRepository;
import com.khk.backjoonrecommender.service.BaekJoonProblemCollector;
import com.khk.backjoonrecommender.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecommendationBasicService implements RecommendationService {
	private final UserRepository userRepository;
	private final ProblemRepository problemRepository;
	private final BaekJoonProblemCollector baekJoonProblemCollector;

	private Setting userSetting = new Setting();

	@Override
	public BasicResponseDto<Problem> recommendProblem(Authentication authentication) throws IOException {
		String loginUsername = authentication.getName();
		User loginUser = userRepository.findByUsername(loginUsername);
		int remainedTrialCnt = loginUser.getReloadCount();
		if (remainedTrialCnt <= 0) {
			// ***못하게 막아야 함.
		}

		userSetting = loginUser.getSetting();
		String userBaekJoonId = loginUser.getBaekJoonId();

		Set<Integer> levelFilter = getLevelFilter(); // 사용자가 설정한 난이도 필터
		Set<String> tagFilter = getTagFilter(); // 사용자가 설정한 문제유형 필터
		Set<Long> userSolvedFilter = getUserSolvedFilter(userBaekJoonId); // 사용자가 이미 해결한 문제 번호 핕터

		List<Problem> problemList = problemRepository.findAll();

		List<Problem> filteredProblemList = problemList.stream()
				.filter(p -> levelFilter.contains(p.getLevel())) // 난이도로 필터링
				.filter(p -> tagFiltering(p.getTags(), tagFilter)) // 문제 유형으로 필터링
				.filter(Predicate.not(p -> userSolvedFilter.contains(p.getId()))) // 풀었던 문제는 제거
				.collect(Collectors.toList());

		Problem recommendedProblem = filteredProblemList.get(0); // ***random 적용 해야함. 아직 안한 상태
		BasicResponseDto<Problem> result = new BasicResponseDto<>();
		result.setData(recommendedProblem);
//		List<Problem> levelFilteredProblemList = problemList.stream()
//				.filter(p -> levelFilter.contains(p.getLevel()))
//				.collect(Collectors.toList());
//
//		List<Problem> tagFilteredProblemList = levelFilteredProblemList.stream()
//				.filter(p -> tagFiltering(p.getTags(), tagFilter))
//				.collect(Collectors.toList());
//
//		List<Problem> eraseSolvedProblemList = tagFilteredProblemList.stream()
//				.filter(p -> !userSolvedFilter.contains(p.getId()))
//				.collect(Collectors.toList());
		return result;
	}

	private HashSet<Long> getUserSolvedFilter(String userBaekJoonId) throws IOException {
		List<Long> userSolvedProblemIdList = baekJoonProblemCollector.getProblemIdListByBaekJoonId(userBaekJoonId);
		return new HashSet<>(userSolvedProblemIdList);
	}

	private boolean tagFiltering(String problemTagsInfo, Set<String> filter) {
		String[] problemTags = problemTagsInfo.split(",");
		return Arrays.stream(problemTags)
				.anyMatch(filter::contains);
	}

	private Set<String> getTagFilter() {
		Option userRecommendationOption = userSetting.getOption();
		String[] tags;
		if (userRecommendationOption.equals(Option.TODAY)) {
			tags = userSetting.getTags().split(",");
		} else {
			// 오늘의 요일에 따른 추천을 해야 함.
			tags = getTodayTags();
		}
		return new HashSet<>(Arrays.asList(tags));
	}

	private String[] getTodayTags() {
		int dayInfo = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		String[] tags;
		switch (dayInfo) {
			case 1: // SUN
				tags = userSetting.getSun().split(",");
				break;
			case 2:
				tags = userSetting.getMon().split(",");
				break;
			case 3:
				tags = userSetting.getTue().split(",");
				break;
			case 4:
				tags = userSetting.getWed().split(",");
				break;
			case 5:
				tags = userSetting.getThu().split(",");
				break;
			case 6:
				tags = userSetting.getFri().split(",");
				break;
			case 7:
				tags = userSetting.getSat().split(",");
				break;
			default:
				tags = new String[0];
				break;
		}

		return tags;
	}

	private Set<Integer> getLevelFilter() {
		String[] levels = userSetting.getLevels().split(",");
		return Arrays.stream(levels)
				.map(Integer::parseInt)
				.collect(Collectors.toSet());
	}

	@Override
	public BasicResponseDto<?> checkProblemIfSolved() {
		// ***getUserSolvedFilter 로 set 자료구조 반환 받은 후,
		// parameter 로 들어온 판단하고 싶은 문제번호와 비교하면 된다.
		return null;
	}

	@Override
	public BasicResponseDto<?> findAdditionalProblem() {
		return null;
	}

	@Override
	public BasicResponseDto<?> reloadProblem() {
		return null;
	}
}
