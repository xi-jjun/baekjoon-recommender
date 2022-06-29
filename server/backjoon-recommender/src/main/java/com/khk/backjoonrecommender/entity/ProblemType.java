package com.khk.backjoonrecommender.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
