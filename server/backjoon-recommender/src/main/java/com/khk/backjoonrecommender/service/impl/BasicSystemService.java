package com.khk.backjoonrecommender.service.impl;

import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.entity.User;
import com.khk.backjoonrecommender.repository.ProblemRepository;
import com.khk.backjoonrecommender.repository.UserRepository;
import com.khk.backjoonrecommender.service.BaekJoonApiService;
import com.khk.backjoonrecommender.service.SystemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BasicSystemService implements SystemService {
	private final BaekJoonApiService baekJoonApiService;
	private final ProblemRepository problemRepository;
	private final UserRepository userRepository;

	@Transactional
	@Override
	public BasicResponseDto<?> migrateBaekJoonProblems(Authentication authentication) throws IOException, ParseException {
		List<Problem> existedProblemList = problemRepository.findAll();
		Set<Long> existedProblemIdList = existedProblemList.stream()
				.map(Problem::getId)
				.collect(Collectors.toSet());

		List<Long> responseData = new ArrayList<>();
		List<Long> latestProblemIdList = baekJoonApiService.getAllProblemIdListFromBaekJoon();
		for (Long problemId : latestProblemIdList) {
			if (!existedProblemIdList.contains(problemId)) {
				Problem problem = baekJoonApiService.getProblemByProblemId(problemId);
				problemRepository.save(problem);
				responseData.add(problemId);
				log.info("Problem no.{} is added to server", problemId);
			}
		}

		BasicResponseDto<List<Long>> responseDto = new BasicResponseDto<>();
		responseDto.setCode(200);
		responseDto.setMessage("success to migrate baekJoon latest problem list");
		responseDto.setData(responseData);

		return responseDto;
	}

	@Transactional
	@Override
	public BasicResponseDto<?> resetUsersReloadCount() {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			user.resetReloadCount();
		}

		BasicResponseDto<?> responseDto = new BasicResponseDto<>();
		responseDto.setCode(200);
		responseDto.setMessage("success to reset users reloadCount");

		return responseDto;
	}

	@Transactional
	@Override
	public BasicResponseDto<?> resetUserReloadCount(Long userId) {
		Optional<User> findUser = userRepository.findById(userId);
		if (findUser.isPresent()) {
			User user = findUser.get();
			user.resetReloadCount();

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

	@Override
	@Transactional
	public void resetDailyReloadCount() {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			user.resetReloadCount();
		}
	}
}
