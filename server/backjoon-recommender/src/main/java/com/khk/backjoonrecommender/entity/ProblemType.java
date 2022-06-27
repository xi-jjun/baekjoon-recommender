package com.khk.backjoonrecommender.entity;

import javax.persistence.*;

@Entity
public class ProblemType {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Problem problem;

    @ManyToOne
    private Type type;
}
