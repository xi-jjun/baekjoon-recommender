package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.entity.SolvingStatus;
import com.khk.backjoonrecommender.entity.TriedProblem;
import com.khk.backjoonrecommender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TriedProblemRepository extends JpaRepository<TriedProblem, Long> {
	/**
	 * 사용자가 풀려고 시도했던 문제들 중에서 문제를 추천받은 날짜에 해당하는 문제 목록 조회
	 *
	 * @param user            문제 풀려고 시도한 사용자
	 * @param recommendedDate 문제들을 추천받은 날짜
	 * @return problem list
	 */
	@Query("SELECT tp FROM TriedProblem tp WHERE tp.user = :user AND tp.recommendedDate = :recommendedDate")
	List<TriedProblem> findByUserAndRecommendedDate(User user, LocalDate recommendedDate);

	Optional<TriedProblem> findByUserAndProblem(User user, Problem problem);

	List<TriedProblem> findByUserAndSolvingStatus(User user, SolvingStatus solvingStatus);
}
