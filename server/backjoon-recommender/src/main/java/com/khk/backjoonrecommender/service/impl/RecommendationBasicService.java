package com.khk.backjoonrecommender.service.impl;

import com.khk.backjoonrecommender.controller.dto.request.SettingRequestDto;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.entity.Option;
import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.entity.Setting;
import com.khk.backjoonrecommender.entity.SolveType;
import com.khk.backjoonrecommender.entity.TriedProblem;
import com.khk.backjoonrecommender.entity.User;
import com.khk.backjoonrecommender.repository.ProblemRepository;
import com.khk.backjoonrecommender.repository.TriedProblemRepository;
import com.khk.backjoonrecommender.repository.UserRepository;
import com.khk.backjoonrecommender.service.BaekJoonApiService;
import com.khk.backjoonrecommender.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RecommendationBasicService implements RecommendationService {
	private final UserRepository userRepository;
	private final ProblemRepository problemRepository;
	private final BaekJoonApiService baekJoonApiService;
	private final TriedProblemRepository triedProblemRepository;

	private Setting userSetting = new Setting();

	@Override
	public BasicResponseDto<?> recommendProblem(Authentication authentication) {
		User loginUser = getLoginUser(authentication);

		Option userOption = userSetting.getOption();
		if (userOption == null || !userOption.equals(Option.TEMP)) {
			userSetting = loginUser.getSetting();
		}

		Set<Integer> levelFilter = getLevelFilter(); // 사용자가 설정한 난이도 필터
		Set<String> tagFilter = getTagFilter(); // 사용자가 설정한 문제유형 필터
		Set<Long> userSolvedFilter = getUserSolvedProblemFilterFromServer(loginUser); // 사용자가 이미 해결한 문제 번호 핕터

		List<Problem> filteredProblemList = getFilteredProblemList(levelFilter, tagFilter, userSolvedFilter);

		Problem recommendedProblem = getRandomProblem(filteredProblemList);
		BasicResponseDto<Problem> result = new BasicResponseDto<>();
		result.setCode(200);
		result.setMessage("success to recommend baek joon problem");
		result.setData(recommendedProblem);

		return result;
	}

	/**
	 * Server 로 부터, 로그인된 사용자가 푼 문제 번호 목록 가져와서 Set 으로 반환
	 * @param loginUser 사용자 Entity
	 * @return 푼 문제가 없다면, 의미 없는 값 0과 1을 넣어서 Set 으로 반환.
	 * 		   푼 문제가 있다면, 해당 문제 번호들을 Set 으로 반환
	 */
	private Set<Long> getUserSolvedProblemFilterFromServer(User loginUser) {
		List<TriedProblem> triedProblemList = loginUser.getTriedProblemList();
		if (triedProblemList.isEmpty()) {
			return Set.of(0L, 1L);
		}

		return triedProblemList.stream()
				.filter(TriedProblem::solved)
				.map(p -> p.getProblem().getId())
				.collect(Collectors.toSet());
	}

	private Problem getRandomProblem(List<Problem> filteredProblemList) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int randomPickedNumber = random.nextInt(filteredProblemList.size());

		return filteredProblemList.get(randomPickedNumber);
	}

	/**
	 * 직접 백준 사이트에서 사용자가 푼 문제 번호 목록 가져와서 Set 으로 반환
	 * @param baekJoonId 사용자 백준 아이디
	 * @return 사용자가 푼 문제 Set
	 * @throws IOException BaekJoonApiService 에서 크롤링 오류가 발생하면 IOException 발생
	 */
	private Set<Long> getUserSolvedProblemFilterFromBaekJoon(String baekJoonId) throws IOException {
		List<Long> userSolvedProblemIdList = baekJoonApiService.getSolvedProblemIdListByBaekJoonId(baekJoonId);
		return new HashSet<>(userSolvedProblemIdList);
	}

	private boolean tagFiltering(String problemTagsInfo, Set<String> filter) {
		String[] problemTags = problemTagsInfo.split(",");
		return Arrays.stream(problemTags)
				.anyMatch(filter::contains);
	}

	private Set<String> getTagFilter() {
		Option option = userSetting.getOption();
		String[] tags;
		if (option.equals(Option.WEEKLY)) {
			// 오늘의 요일에 따른 추천을 해야 함.
			tags = getTodayTags();
		} else {
			tags = userSetting.getTags().split(",");
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

	@Transactional
	@Override
	public BasicResponseDto<?> checkProblemIfSolved(Authentication authentication, Long problemId) throws IOException {
		User loginUser = getLoginUser(authentication);
		String userBaekJoonId = loginUser.getBaekJoonId();
		Set<Long> solvedProblemIdList = getUserSolvedProblemFilterFromBaekJoon(userBaekJoonId);
		Problem problem = problemRepository.findById(problemId).orElse(null);

		if (solvedProblemIdList.contains(problemId)) {
			TriedProblem triedProblem = TriedProblem.builder()
					.problem(problem)
					.user(loginUser)
					.solvedDate(LocalDateTime.now())
					.isSolved(SolveType.PASS)
					.build();
			triedProblemRepository.save(triedProblem);

			BasicResponseDto<?> responseDto = new BasicResponseDto<>();
			responseDto.setCode(200);
			responseDto.setMessage(userBaekJoonId + " solved problem id=" + problemId);

			return responseDto;
		}

		BasicResponseDto<?> responseDto = new BasicResponseDto<>();
		responseDto.setCode(400);
		responseDto.setMessage(userBaekJoonId + " didn't solve problem id=" + problemId);

		return responseDto;
	}

	@Override
	public BasicResponseDto<?> findAdditionalProblem(Authentication authentication, SettingRequestDto settingRequestDto) throws IOException {
		Setting tempSetting = settingRequestDto.toEntity();
		userSetting = tempSetting;

		return recommendProblem(authentication);
	}

	private User getLoginUser(Authentication authentication) {
		String loginUsername = authentication.getName();
		return userRepository.findByUsername(loginUsername);
	}

	private List<Problem> getFilteredProblemList(Set<Integer> levelFilter, Set<String> tagFilter, Set<Long> userSolvedFilter) {
		List<Problem> problemList = problemRepository.findAll();

		return problemList.stream()
				.filter(p -> levelFilter.contains(p.getLevel())) // 난이도로 필터링
				.filter(p -> tagFiltering(p.getTags(), tagFilter)) // 문제 유형으로 필터링
				.filter(Predicate.not(p -> userSolvedFilter.contains(p.getId()))) // 풀었던 문제는 제거
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public BasicResponseDto<?> reloadProblem(Authentication authentication) throws IOException {
		User loginUser = getLoginUser(authentication);
		int remainedTrialCnt = loginUser.getReloadCount();
		if (remainedTrialCnt <= 0) {
			BasicResponseDto<?> responseDto = new BasicResponseDto<>();
			responseDto.setCode(400);
			responseDto.setMessage("user reload count is zero");

			return responseDto;
		}
		loginUser.decreaseReloadCount();

		return recommendProblem(authentication);
	}
}
