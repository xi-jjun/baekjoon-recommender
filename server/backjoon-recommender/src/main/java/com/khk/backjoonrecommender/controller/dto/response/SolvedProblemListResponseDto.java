package com.khk.backjoonrecommender.controller.dto.response;

import com.khk.backjoonrecommender.entity.Problem;
import com.khk.backjoonrecommender.entity.SolveType;
import com.khk.backjoonrecommender.entity.TriedProblem;
import com.khk.backjoonrecommender.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolvedProblemListResponseDto {
	private Problem problem;
	private String username;
	private String baekJoonId;
	private SolveType isSolved;
	private LocalDateTime solvedDate;

	public SolvedProblemListResponseDto(TriedProblem triedProblem) {
		User solver = triedProblem.getUser();
		this.problem = triedProblem.getProblem();
		this.username = solver.getUsername();
		this.baekJoonId = solver.getBaekJoonId();
		this.isSolved = triedProblem.getIsSolved();
		this.solvedDate = getSolvedDate();
	}
}
