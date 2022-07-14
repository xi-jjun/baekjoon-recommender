package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.TriedProblem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TriedProblemRepository extends JpaRepository<TriedProblem, Long> {
}
