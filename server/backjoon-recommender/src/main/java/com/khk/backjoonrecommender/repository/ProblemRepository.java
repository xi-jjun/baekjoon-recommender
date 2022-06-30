package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
	List<Problem> findByUser(User user);
}
