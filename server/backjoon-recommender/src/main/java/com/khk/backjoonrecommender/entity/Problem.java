package com.khk.backjoonrecommender.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Problem {

    @Id
    @GeneratedValue
    private Long id; 

    private String title;
    private int level;
    private String tags;
    private int solvedCount;
    private Double answerRate;
}
