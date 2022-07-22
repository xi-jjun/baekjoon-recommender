package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.entity.SolvingStatus;
import com.khk.backjoonrecommender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
	Problem findByTitle(String title);

	@Query("SELECT DISTINCT pt.problem " +
			"FROM ProblemTag pt, TriedProblem tp " +
			"WHERE pt.problem.level IN :levels " +
			"AND pt.tag.tagName IN :tags " +
			"AND pt.problem.id NOT IN :solved")
	List<Problem> findDistinctProblemListByLevelInAndTagInAndSolvedNotIn(Set<Integer> levels, Set<String> tags, Set<Long> solved);

	@Query("SELECT tp.problem.id FROM TriedProblem tp WHERE tp.user = :user AND tp.solvingStatus = :solvingStatus")
	List<Long> findProblemsIdByUserAndSolvingStatus(User user, SolvingStatus solvingStatus);

	List<Problem> findProblemsByIdIn(Collection<Long> ids);
}
