package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.ProblemTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemTagRepository extends JpaRepository<ProblemTag, Long> {
}
