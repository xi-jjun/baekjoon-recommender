package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
	Problem findByTitle(String title);
}
