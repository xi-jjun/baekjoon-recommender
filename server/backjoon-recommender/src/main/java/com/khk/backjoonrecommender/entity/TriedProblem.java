package com.khk.backjoonrecommender.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class TriedProblem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	private User user;

	@ManyToOne(targetEntity = Problem.class, fetch = FetchType.LAZY)
	private Problem problem;

	private SolveType isSolved;
	private LocalDateTime solvedDate;

	public void updateSolvedStatus(SolveType solveType) {
		this.isSolved = solveType;
	}
}
