package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.entity.SolveType;
import com.khk.backjoonrecommender.entity.TriedProblem;
import com.khk.backjoonrecommender.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class TriedProblemRepositoryTest {
	@Autowired
	private EntityManager em;

	@Autowired
	private TriedProblemRepository triedProblemRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProblemRepository problemRepository;

	@Transactional
	@BeforeAll
	void makeInitTestData() {
		// given problem list
		for (int i = 1; i <= 5; i++) {
			Problem problem = Problem.builder()
					.id(1000L + i)
					.title("problem title no."+i)
					.level(10+i)
					.tags("math")
					.problemUrl("url")
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

	@Transactional
	@Test
	void addSolvedProblem() {
		// given
		User solver = userRepository.findByUsername("kjj");
		Problem problem = problemRepository.findById(1002L).orElse(null);
		Problem problem2 = problemRepository.findById(1003L).orElse(null);

		// when : 사용자가 1002 번 문제를 풀어서 표시를 해야하는 상황
		TriedProblem triedProblem = TriedProblem.builder()
				.problem(problem)
				.user(solver)
				.solvedDate(LocalDateTime.now())
				.isSolved(SolveType.PASS)
				.build();

		TriedProblem triedProblem2 = TriedProblem.builder()
				.problem(problem2)
				.user(solver)
				.solvedDate(LocalDateTime.now())
				.isSolved(SolveType.PASS)
				.build();

		triedProblemRepository.save(triedProblem);
		triedProblemRepository.save(triedProblem2);

		em.flush();
		em.clear();

		// then
		User problemsSolver = userRepository.findByUsername("kjj");
		List<TriedProblem> triedProblemList = problemsSolver.getTriedProblemList();
		for (TriedProblem triedProblem1 : triedProblemList) {
			System.out.println("triedProblem1.getProblem().getTitle() = " + triedProblem1.getProblem().getTitle());
		}
	}
}