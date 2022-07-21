package com.khk.backjoonrecommender.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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

	@ManyToOne(targetEntity = Problem.class, fetch = FetchType.EAGER)
	private Problem problem;

	@Enumerated(EnumType.STRING)
	private SolvingStatus solvingStatus;
	private LocalDateTime solvedDate;
	private LocalDate recommendedDate;

	public boolean solved() {
		return this.solvingStatus.equals(SolvingStatus.PASS);
	}

	public boolean isRecommendedToday() {
		final LocalDate TODAY = LocalDate.now();
		return TODAY.equals(this.recommendedDate);
	}

	public boolean solving() {
		return this.solvingStatus.equals(SolvingStatus.SOLVING);
	}

	public boolean isSameProblem(Long problemId) {
		return this.getProblem().getId().equals(problemId);
	}

	public void updateSolvedStatus(SolvingStatus solvingStatus) {
		if (solvingStatus.equals(SolvingStatus.PASS)) {
			this.solvedDate = LocalDateTime.now();
		}
		this.solvingStatus = solvingStatus;
	}
}
