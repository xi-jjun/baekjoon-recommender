package com.khk.backjoonrecommender.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Problem {

    @Id
    @GeneratedValue
    private Long id;

    private int level;

    private String type;

    @Enumerated(value = EnumType.STRING)
    private SolveType isSolved;

    private LocalDateTime solvedDate;

    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    private List<ProblemType> problemTypes;

}
