package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.entity.SolvingStatus;
import com.khk.backjoonrecommender.entity.TriedProblem;
import com.khk.backjoonrecommender.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
class TriedProblemRepositoryTest {
	@Autowired
	private TriedProblemRepository triedProblemRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProblemRepository problemRepository;

	@BeforeAll
	void makeInitTestData() {
		// given problem list
		for (int i = 1; i <= 5; i++) {
			Problem problem = Problem.builder()
					.id(1000L + i)
					.title("problem title no." + i)
					.level(10 + i)
					.build();

			problemRepository.save(problem);
		}

		// given user
		User user = User.builder()
				.username("kjj")
				.baekJoonId("rlawowns000")
				.password("123")
				.build();
		userRepository.save(user);
	}

	@Test
	void addSolvedProblem() {
		// given : kjj 라는 사용자와 2개의 문제
		User solver = userRepository.findByUsername("kjj");
		Problem problem = problemRepository.findById(1002L).orElse(null);
		Problem problem2 = problemRepository.findById(1003L).orElse(null);
		Set<Long> solvedIdSet = new HashSet<>();
		solvedIdSet.add(1002L);
		solvedIdSet.add(1003L);

		// when : kjj 사용자가 1002, 1003 번 문제를 풀어서 표시를 해야하는 상황
		TriedProblem triedProblem = TriedProblem.builder()
				.problem(problem)
				.user(solver)
				.solvedDate(LocalDateTime.now())
				.solvingStatus(SolvingStatus.PASS)
				.build();

		TriedProblem triedProblem2 = TriedProblem.builder()
				.problem(problem2)
				.user(solver)
				.solvedDate(LocalDateTime.now())
				.solvingStatus(SolvingStatus.PASS)
				.build();

		triedProblemRepository.save(triedProblem);
		triedProblemRepository.save(triedProblem2);

		// then : tried problem 에 1002, 1003 번 문제가 풀었다는 표시가 돼있어야 함
		User problemsSolver = userRepository.findByUsername("kjj");
		List<TriedProblem> solvedProblems = triedProblemRepository.findByUserAndSolvingStatus(problemsSolver, SolvingStatus.PASS);
		for (TriedProblem solvedProblem : solvedProblems) {
			assertThat(solvedProblem.getSolvingStatus()).isEqualTo(SolvingStatus.PASS);
			Long solvedProblemId = solvedProblem.getProblem().getId();
			assertThat(solvedIdSet.contains(solvedProblemId)).isTrue();
		}
	}

	@DisplayName("사용자 정보와 문제를 추천받은 날짜에 해당하는 tried problem 을 조회")
	@Test
	void findTriedProblemsByUserAndRecommendedDate() {
		// given : 문제를 시도한 사용자와 추천받은 문제 1001번 정보
		User solver = userRepository.findByUsername("kjj");
		Problem recommendedProblem = problemRepository.findById(1001L).get();
		TriedProblem triedProblem = TriedProblem.builder()
				.user(solver)
				.problem(recommendedProblem)
				.solvingStatus(SolvingStatus.SOLVING)
				.recommendedDate(LocalDate.now())
				.build();

		triedProblemRepository.save(triedProblem);

		// when : 사용자가 시도한 문제 중에서 추천받은 날짜에 해당하는 시도한 문제를 가져올 때
		List<TriedProblem> triedProblems = triedProblemRepository.findByUserAndRecommendedDate(solver, LocalDate.now());

		// then : 사용자가 오늘 추천받아서 시도한 문제번호는 1001 번 문제여야 함 + kjj 가 시도한 문제여야 함
		TriedProblem todayTriedProblem = triedProblems.get(0);
		assertThat(todayTriedProblem.getProblem().getId()).isEqualTo(1001L);
		assertThat(todayTriedProblem.getUser().getUsername()).isEqualTo("kjj");
	}

	@DisplayName("사용자가 어떤 문제를 추천받았다면, tried problem 에 저장됐기 때문에 사용자 정보와 문제 정보로 조회가 가능해야 한다")
	@Test
	void findByUserAndProblem() {
		// given : 문제를 시도한 사용자와 추천받은 문제 1001번 정보
		User solver = userRepository.findByUsername("kjj");
		Problem recommendedProblem = problemRepository.findById(1001L).get();
		TriedProblem triedProblem = TriedProblem.builder()
				.user(solver)
				.problem(recommendedProblem)
				.solvingStatus(SolvingStatus.SOLVING)
				.recommendedDate(LocalDate.now())
				.build();

		triedProblemRepository.save(triedProblem);

		// when : 사용자가 해당 1001번 문제를 추천 받았는지 찾을 때
		Optional<TriedProblem> findTriedProblem = triedProblemRepository.findByUserAndProblem(solver, recommendedProblem);

		// then : 해당 문제는 존재해야 한다.
		assertThat(findTriedProblem.isPresent()).isTrue();
	}

	@DisplayName("사용자가 어떤 문제를 추천 못 받았을 때는 tried problem 에서 사용자 정보와 문제 정보로 조회가 불가능해야 한다")
	@Test
	void findByUserAndProblemIfNotRecommended() {
		// given : 문제를 시도한 사용자와 추천받은 문제 1001번 정보.
		User solver = userRepository.findByUsername("kjj");
		Problem recommendedProblem = problemRepository.findById(1001L).get();
		Problem otherProblem = problemRepository.findById(1003L).get();
		TriedProblem triedProblem = TriedProblem.builder()
				.user(solver)
				.problem(recommendedProblem)
				.solvingStatus(SolvingStatus.SOLVING)
				.recommendedDate(LocalDate.now())
				.build();
		triedProblemRepository.save(triedProblem);

		// when : 근데 시도한적이 없는 1003번 문제에 대한 정보로 조회를 하려고 할 때
		Optional<TriedProblem> triedProblemOptional = triedProblemRepository.findByUserAndProblem(solver, otherProblem);

		// then : 존재하면 안된다. 사용자가 현재 시도한 문제는 1001번 문제 말고는 없다. 1003번 문제가 될 수 없다.
		assertThat(triedProblemOptional.isPresent()).isFalse();
	}

	@Test
	void findByUserAndSolvingStatus() {
		// given : 문제를 시도한 사용자와 추천받은 문제 1001번 정보. 아직 푸는 중인 상태
		User solver = userRepository.findByUsername("kjj");
		Problem recommendedProblem = problemRepository.findById(1001L).get();
		TriedProblem triedProblem = TriedProblem.builder()
				.user(solver)
				.problem(recommendedProblem)
				.solvingStatus(SolvingStatus.SOLVING)
				.recommendedDate(LocalDate.now())
				.build();
		triedProblemRepository.save(triedProblem);

		// when : 사용자가 풀고 있는 시도한 문제 정보 조회
		List<TriedProblem> solvingProblems = triedProblemRepository.findByUserAndSolvingStatus(solver, SolvingStatus.SOLVING);

		// then : 1001번 문제를 풀고 있던 중이었기에 해당 문제가 조회돼야 함
		TriedProblem triedOne = solvingProblems.get(0);
		Long solvingProblemId = triedOne.getProblem().getId();
		assertThat(solvingProblemId).isEqualTo(1001L);
	}
}