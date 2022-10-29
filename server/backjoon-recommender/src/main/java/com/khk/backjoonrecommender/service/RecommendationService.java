package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.request.SettingRequestDto;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.RecommendProblemResponseDto;
import com.khk.backjoonrecommender.entity.Option;
import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.entity.Setting;
import com.khk.backjoonrecommender.entity.SolvingStatus;
import com.khk.backjoonrecommender.entity.TriedProblem;
import com.khk.backjoonrecommender.entity.User;
import com.khk.backjoonrecommender.repository.ProblemRepository;
import com.khk.backjoonrecommender.repository.TriedProblemRepository;
import com.khk.backjoonrecommender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RecommendationService {
	private final UserRepository userRepository;
	private final ProblemRepository problemRepository;
	private final BaekJoonApiService baekJoonApiService;
	private final TriedProblemRepository triedProblemRepository;

	private Setting userSetting = new Setting();

	@Transactional
	public BasicResponseDto<?> recommendProblem(Authentication authentication) {
		log.info("recommendation start");
		User loginUser = getLoginUser(authentication);

		Option userOption = userSetting.getOption();
		if (userOption == null || !userOption.equals(Option.TEMP)) {
			userSetting = loginUser.getSetting();
		}

		List<Problem> filteredProblemListByLevelsAndTagsAndNotSolved = recommendationFiltering(loginUser);

		Problem recommendedProblem = getRandomProblem(filteredProblemListByLevelsAndTagsAndNotSolved);

		TriedProblem todayRecommended = saveTodayRecommendedProblemToUserTriedProblem(loginUser, recommendedProblem);

		BasicResponseDto<RecommendProblemResponseDto> result = new BasicResponseDto<>();
		result.setCode(200);
		result.setMessage("success to recommend baek joon problem");
		result.setData(new RecommendProblemResponseDto(todayRecommended));

		log.info("recommendation end");

		return result;
	}

	private TriedProblem saveTodayRecommendedProblemToUserTriedProblem(User loginUser, Problem recommendedProblem) {
		TriedProblem todayRecommended = TriedProblem.builder()
				.user(loginUser)
				.problem(recommendedProblem)
				.solvingStatus(SolvingStatus.SOLVING)
				.recommendedDate(LocalDate.now())
				.build();
		triedProblemRepository.save(todayRecommended);
		return todayRecommended;
	}

	private List<Problem> recommendationFiltering(User loginUser) {
		Set<Long> solvedProblemsId = getSolvedProblemsId(loginUser);
		Set<String> userSelectedProblemTags = userSetting.getUserRecommendationTags();
		Set<Integer> userSelectedProblemLevels = userSetting.getUserRecommendationLevels();
		List<Problem> result = problemRepository.findDistinctProblemListByLevelInAndTagInAndSolvedNotIn(userSelectedProblemLevels, userSelectedProblemTags, solvedProblemsId);
		return result;
	}

	private Set<Long> getSolvedProblemsId(User loginUser) {
		List<Long> solvedProblemIds = problemRepository.findProblemsIdByUserAndSolvingStatus(loginUser, SolvingStatus.PASS);
		return new HashSet<>(solvedProblemIds);
	}

	/**
	 * 오늘 사용자가 추천받은 문제 목록을 정보 조회
	 *
	 * @param authentication 로그인된 사용자 객체
	 * @return 오늘 날짜 기준으로 추천 받은 문제 정보
	 */
	public BasicResponseDto<List<RecommendProblemResponseDto>> getTodayRecommendedProblemListByUser(Authentication authentication) {
		User loginUser = getLoginUser(authentication);

		List<TriedProblem> recommendedToday = triedProblemRepository.findByUserAndRecommendedDate(loginUser, LocalDate.now());
		List<RecommendProblemResponseDto> todayRecommendedList = recommendedToday.stream()
				.map(RecommendProblemResponseDto::new)
				.collect(Collectors.toList());

		if (todayRecommendedList.isEmpty()) { // 예외처리 필요
			return new BasicResponseDto<>(400, "No recommended today", null);
		}

		BasicResponseDto<List<RecommendProblemResponseDto>> response = new BasicResponseDto<>();
		response.setCode(200);
		response.setMessage("today recommended problem list");
		response.setData(todayRecommendedList);

		return response;
	}


	private Problem getRandomProblem(List<Problem> filteredProblemList) {
		if (filteredProblemList.isEmpty()) { // 예외처리 필요
			throw new IllegalArgumentException("cannot recommend");
		}

		ThreadLocalRandom random = ThreadLocalRandom.current();
		int randomPickedNumber = random.nextInt(filteredProblemList.size());

		return filteredProblemList.get(randomPickedNumber);
	}

	/**
	 * 직접 백준 사이트에서 사용자가 푼 문제 번호 목록 가져와서 Set 으로 반환
	 *
	 * @param baekJoonId 사용자 백준 아이디
	 * @return 사용자가 푼 문제 Set
	 * @throws IOException BaekJoonApiService 에서 크롤링 오류가 발생하면 IOException 발생
	 */
	private Set<Long> getUserSolvedProblemFilterFromBaekJoon(String baekJoonId) throws IOException {
		List<Long> userSolvedProblemIdList = baekJoonApiService.getSolvedProblemIdListByBaekJoonId(baekJoonId);
		return new HashSet<>(userSolvedProblemIdList);
	}

	/**
	 * problem id 에 해당하는 문제가 풀렸는지 여부를 알기 위한 기능.
	 * if solved, then TriedProblem 에서 problem id 에 해당하는 객체에 풀렸다는 표시를 한다.
	 * else, 안풀렸다는 표시를 하거나, 문제가 존재하지 않는 것
	 *
	 * @param authentication 로그인된 사용자 객체
	 * @param problemId      현재 풀었는지 여부가 궁금한 문제번호
	 * @return 푼 문제에 대한 응답
	 * @throws IOException
	 */
	@Transactional
	public BasicResponseDto<?> checkProblemIfSolved(Authentication authentication, Long problemId) throws IOException {
		User loginUser = getLoginUser(authentication);
		String userBaekJoonId = loginUser.getBaekJoonId();
		Set<Long> solvedProblemIdList = getUserSolvedProblemFilterFromBaekJoon(userBaekJoonId);
		Optional<Problem> problem = problemRepository.findById(problemId);

		if (problem.isEmpty()) {
			throw new IllegalArgumentException("no such problem");
		}

		if (solvedProblemIdList.contains(problemId)) { // 백준에서 푼 문제 번호 중에 현재 problem id 가 있다면,
			Optional<TriedProblem> triedProblem = triedProblemRepository.findByUserAndProblem(loginUser, problem.get());
			if (triedProblem.isPresent()) {
				TriedProblem solvedProblem = triedProblem.get();
				solvedProblem.updateSolvedStatus(SolvingStatus.PASS);

				return new BasicResponseDto<>(200, "solved problem", new RecommendProblemResponseDto(solvedProblem));
			}

			return new BasicResponseDto<>(400, "problem is not existed", null);
		}

		BasicResponseDto<?> responseDto = new BasicResponseDto<>();
		responseDto.setCode(400);
		responseDto.setMessage(userBaekJoonId + " didn't solve problem id=" + problemId);

		return responseDto;
	}

	public BasicResponseDto<?> recommendAdditionalProblem(Authentication authentication, SettingRequestDto settingRequestDto) {
		Setting tempSetting = settingRequestDto.toEntity();
		userSetting = tempSetting;

		return recommendProblem(authentication);
	}

	private User getLoginUser(Authentication authentication) {
		String loginUsername = authentication.getName();
		return userRepository.findByUsername(loginUsername);
	}

	@Transactional
	public BasicResponseDto<?> refreshRecommendation(Authentication authentication) {
		User loginUser = getLoginUser(authentication);

		if (loginUser.hasNoRefreshCount()) {
			BasicResponseDto<?> responseDto = new BasicResponseDto<>();
			responseDto.setCode(400);
			responseDto.setMessage("user reload count is zero");

			return responseDto;
		}
		loginUser.decreaseReloadCount();

		// 해당 사용자에게 추천된 문제 중, '오늘 추천받았던' 문제 중, '안 풀었던 문제' 삭제.
		deleteLastRecommendedProblem(loginUser);

		// 이후 추천 => recommendProblem(auth) 다.
		return recommendProblem(authentication);
	}

	private void deleteLastRecommendedProblem(User loginUser) {
		List<TriedProblem> recommendedToday = triedProblemRepository.findByUserAndRecommendedDate(loginUser, LocalDate.now());
		Optional<TriedProblem> notSolvedProblemToday = getNotSolvedProblemAndRecommendedToday(recommendedToday);

		notSolvedProblemToday.ifPresent(triedProblemRepository::delete);
	}

	private Optional<TriedProblem> getNotSolvedProblemAndRecommendedToday(List<TriedProblem> recommendedToday) {
		return recommendedToday.stream()
				.filter(TriedProblem::solving)
				.findFirst();
	}
}

