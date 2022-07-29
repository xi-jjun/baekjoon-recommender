package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.request.SettingRequestDto;
import com.khk.backjoonrecommender.controller.dto.request.UserRegisterRequestDto;
import com.khk.backjoonrecommender.controller.dto.request.UserRequestDto;
import com.khk.backjoonrecommender.controller.dto.response.*;
import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.entity.Setting;
import com.khk.backjoonrecommender.entity.SolvingStatus;
import com.khk.backjoonrecommender.entity.TriedProblem;
import com.khk.backjoonrecommender.entity.User;
import com.khk.backjoonrecommender.exception.BaekJoonIdNotFoundException;
import com.khk.backjoonrecommender.exception.AlreadyRegisteredException;
import com.khk.backjoonrecommender.repository.ProblemRepository;
import com.khk.backjoonrecommender.repository.SettingRepository;
import com.khk.backjoonrecommender.repository.TriedProblemRepository;
import com.khk.backjoonrecommender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.khk.backjoonrecommender.common.ResponseCodeMessage.*;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
	private static final LocalDateTime REGISTER_FIXED_SOLVED_DATE = LocalDateTime.of(2022, Month.JUNE, 25, 0, 0);
	private final ValidationService validationService;
	private final BaekJoonApiService baekJoonApiService;
	private final UserRepository userRepository;
	private final TriedProblemRepository triedProblemRepository;
	private final ProblemRepository problemRepository;
	private final SettingRepository settingRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public BasicResponseDto<MyPageResponseDto> findUser(Authentication authentication) {
		String username = authentication.getName();
		User loginUser = userRepository.findByUsername(username);
		Setting userSetting = loginUser.getSetting();

		MyPageResponseDto myPageResponseDto = new MyPageResponseDto(loginUser, userSetting);
		return new BasicResponseDto<>(SUCCESS, SUCCESS_USER_DETAIL, myPageResponseDto);
	}

	@Transactional
	public BasicResponseDto<?> registerUser(UserRegisterRequestDto userRegisterRequestDto, BindingResult bindingResult) throws IOException {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("값 잘못 입력");
		}
		return registerUser(userRegisterRequestDto);
	}

	@Transactional
	public BasicResponseDto<?> registerUser(UserRegisterRequestDto userRegisterRequestDto) throws IOException {
		UserRequestDto userRequestDTO = userRegisterRequestDto.toUserDto();
		SettingRequestDto settingRequestDTO = userRegisterRequestDto.toSettingDto();

		String baekJoonId = userRequestDTO.getBaekJoonId();
		if (!validationService.validateBaekJoonId(baekJoonId)) {
			throw new BaekJoonIdNotFoundException();
		}

		Setting setting = settingRequestDTO.toEntity();
		settingRepository.save(setting);

		encodingUserPassword(userRequestDTO);
		String username = userRequestDTO.getUsername();
		User findUser = userRepository.findByUsername(username);
		if (findUser != null) {
			throw new AlreadyRegisteredException();
		}
		User user = userRequestDTO.toEntity();
		user.setProblemFilterSetting(setting);
		user.resetRefreshCount();
		userRepository.save(user);

		saveSolvedProblemFromBaekJoonToServer(user);

		BasicResponseDto<User> responseDto = new BasicResponseDto<>();
		responseDto.setCode(SUCCESS);
		responseDto.setMessage(REGISTER_SUCCESS);
		responseDto.setData(null);
		log.info("user {} is created", user.getUsername());

		return responseDto;
	}

	/**
	 * 회원가입 시 사용자 백준 아이디의 이 때까지 푼 문제 전체를 Server 에 반영
	 *
	 * @param user 사용자 Entity
	 * @throws IOException 백준 사이트 크롤링 Exception
	 */
	private void saveSolvedProblemFromBaekJoonToServer(User user) throws IOException {
		String baekJoonId = user.getBaekJoonId();
		List<Long> userSolvedProblemIdList = baekJoonApiService.getSolvedProblemIdListByBaekJoonId(baekJoonId);
		List<Problem> userSolvedProblemList = problemRepository.findProblemsByIdIn(userSolvedProblemIdList);

		userSolvedProblemList
				.forEach(solvedProblem -> saveTriedProblemToDatabase(user, solvedProblem));
	}

	/**
	 * 사용자가 푼 문제를 다 : 다 매핑으로 TriedProblem 으로 Server 에 저장
	 *
	 * @param user          사용자 Entity
	 * @param solvedProblem 사용자가 푼 문제 객체
	 */
	private void saveTriedProblemToDatabase(User user, Problem solvedProblem) {
		TriedProblem passedProblem = TriedProblem.builder()
				.user(user)
				.problem(solvedProblem)
				.solvedDate(REGISTER_FIXED_SOLVED_DATE)
				.solvingStatus(SolvingStatus.PASS)
				.build();
		log.info("user id={} solved problem no.{}", user.getId(), solvedProblem.getId());
		triedProblemRepository.save(passedProblem);
	}

	public BasicResponseDto<List<SolvedProblemListResponseDto>> getSolvedProblemList(Long userId) {
		Optional<User> findResult = userRepository.findById(userId);
		log.info("get solved problem list user id = {}", userId);
		if (findResult.isPresent()) {
			User user = findResult.get();
			List<TriedProblem> solvedProblems = triedProblemRepository.findByUserAndSolvingStatus(user, SolvingStatus.PASS);
			List<SolvedProblemListResponseDto> response = solvedProblems.stream()
					.map(SolvedProblemListResponseDto::new)
					.collect(Collectors.toList());

			log.info("success to get solved problem list");

			return new BasicResponseDto<>(200, "solved problem list user id=" + userId, response);
		}

		return new BasicResponseDto<>(400, "user not founded id=" + userId, null);
	}

	@Transactional
	public BasicResponseDto<?> deleteUserInfo(Authentication authentication) {
		String loginUsername = authentication.getName();
		User loginUser = userRepository.findByUsername(loginUsername);

		userRepository.delete(loginUser);

		return BasicResponseDto.builder()
				.code(200)
				.message("success to delete user username=" + loginUsername)
				.build();
	}

	private void encodingUserPassword(UserRequestDto userRequestDto) {
		String password = userRequestDto.getPassword();
		String encodedPassword = passwordEncoder.encode(password);
		userRequestDto.setPassword(encodedPassword);
	}

	@Transactional
	public BasicResponseDto<?> modifyUser(Authentication authentication, UserRegisterRequestDto userRegisterRequestDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("값 잘못 입력");
		}

		UserRequestDto userRequestDTO = userRegisterRequestDto.toUserDto();
		encodingUserPassword(userRequestDTO);
		SettingRequestDto settingRequestDTO = userRegisterRequestDto.toSettingDto();

		String loginUsername = authentication.getName();
		User loginUser = userRepository.findByUsername(loginUsername);
		loginUser.updateUserInfo(userRequestDTO);

		Setting userSetting = loginUser.getSetting();
		userSetting.updateSettingInfo(settingRequestDTO);

		return BasicResponseDto.builder()
				.code(200)
				.message("success to modify user=" + loginUsername)
				.build();
	}
}

