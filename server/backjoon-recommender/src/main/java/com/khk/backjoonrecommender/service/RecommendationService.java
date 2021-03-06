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
	 * ?????? ???????????? ???????????? ?????? ????????? ?????? ??????
	 *
	 * @param authentication ???????????? ????????? ??????
	 * @return ?????? ?????? ???????????? ?????? ?????? ?????? ??????
	 */
	public BasicResponseDto<List<RecommendProblemResponseDto>> getTodayRecommendedProblemListByUser(Authentication authentication) {
		User loginUser = getLoginUser(authentication);

		List<TriedProblem> recommendedToday = triedProblemRepository.findTriedProblemsByUserAndRecommendedDate(loginUser, LocalDate.now());
		List<RecommendProblemResponseDto> todayRecommendedList = recommendedToday.stream()
				.map(RecommendProblemResponseDto::new)
				.collect(Collectors.toList());

		if (todayRecommendedList.isEmpty()) { // ???????????? ??????
			return new BasicResponseDto<>(400, "No recommended today", null);
		}

		BasicResponseDto<List<RecommendProblemResponseDto>> response = new BasicResponseDto<>();
		response.setCode(200);
		response.setMessage("today recommended problem list");
		response.setData(todayRecommendedList);

		return response;
	}


	private Problem getRandomProblem(List<Problem> filteredProblemList) {
		if (filteredProblemList.isEmpty()) { // ???????????? ??????
			throw new IllegalArgumentException("cannot recommend");
		}

		ThreadLocalRandom random = ThreadLocalRandom.current();
		int randomPickedNumber = random.nextInt(filteredProblemList.size());

		return filteredProblemList.get(randomPickedNumber);
	}

	/**
	 * ?????? ?????? ??????????????? ???????????? ??? ?????? ?????? ?????? ???????????? Set ?????? ??????
	 *
	 * @param baekJoonId ????????? ?????? ?????????
	 * @return ???????????? ??? ?????? Set
	 * @throws IOException BaekJoonApiService ?????? ????????? ????????? ???????????? IOException ??????
	 */
	private Set<Long> getUserSolvedProblemFilterFromBaekJoon(String baekJoonId) throws IOException {
		List<Long> userSolvedProblemIdList = baekJoonApiService.getSolvedProblemIdListByBaekJoonId(baekJoonId);
		return new HashSet<>(userSolvedProblemIdList);
	}

	/**
	 * problem id ??? ???????????? ????????? ???????????? ????????? ?????? ?????? ??????.
	 * if solved, then TriedProblem ?????? problem id ??? ???????????? ????????? ???????????? ????????? ??????.
	 * else, ??????????????? ????????? ?????????, ????????? ???????????? ?????? ???
	 *
	 * @param authentication ???????????? ????????? ??????
	 * @param problemId      ?????? ???????????? ????????? ????????? ????????????
	 * @return ??? ????????? ?????? ??????
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

		if (solvedProblemIdList.contains(problemId)) { // ???????????? ??? ?????? ?????? ?????? ?????? problem id ??? ?????????,
			Optional<TriedProblem> triedProblem = triedProblemRepository.findTriedProblemByUserAndProblem(loginUser, problem.get());
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

		// ?????? ??????????????? ????????? ?????? ???, '?????? ???????????????' ?????? ???, '??? ????????? ??????' ??????.
		deleteLastRecommendedProblem(loginUser);

		// ?????? ?????? => recommendProblem(auth) ???.
		return recommendProblem(authentication);
	}

	private void deleteLastRecommendedProblem(User loginUser) {
		List<TriedProblem> recommendedToday = triedProblemRepository.findTriedProblemsByUserAndRecommendedDate(loginUser, LocalDate.now());
		Optional<TriedProblem> notSolvedProblemToday = getNotSolvedProblemAndRecommendedToday(recommendedToday);

		notSolvedProblemToday.ifPresent(triedProblemRepository::delete);
	}

	private Optional<TriedProblem> getNotSolvedProblemAndRecommendedToday(List<TriedProblem> recommendedToday) {
		return recommendedToday.stream()
				.filter(TriedProblem::solving)
				.findFirst();
	}
}

