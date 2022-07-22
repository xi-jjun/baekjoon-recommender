package com.khk.backjoonrecommender.controller.dto.response;

import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.entity.SolvingStatus;
import com.khk.backjoonrecommender.entity.TriedProblem;
import com.khk.backjoonrecommender.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecommendProblemResponseDto {
	private Problem problem;
	private String username;
	private String baekJoonId;
	private LocalDate recommendedDate;
	private SolvingStatus isSolved;

	public RecommendProblemResponseDto(TriedProblem triedProblem) {
		User user = triedProblem.getUser();
		this.problem = triedProblem.getProblem();
		this.username = user.getUsername();
		this.baekJoonId = user.getBaekJoonId();
		this.recommendedDate = triedProblem.getRecommendedDate();
		this.isSolved = triedProblem.getSolvingStatus();
	}
}
