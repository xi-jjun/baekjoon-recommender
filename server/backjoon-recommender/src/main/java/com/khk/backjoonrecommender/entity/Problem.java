package com.khk.backjoonrecommender.entity;

import javax.persistence.*;
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
