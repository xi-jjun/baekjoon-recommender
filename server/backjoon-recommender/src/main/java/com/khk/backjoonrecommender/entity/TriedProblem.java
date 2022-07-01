package com.khk.backjoonrecommender.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Entity
public class TriedProblem {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	private User user;

	@ManyToOne(targetEntity = Problem.class, fetch = FetchType.LAZY)
	private Problem problem;

	private SolveType isSolved;
	private LocalDateTime solvedDate;
}
