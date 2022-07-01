package com.khk.backjoonrecommender.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Problem {

    @Id
    private Long id;

    private String title;
    private int level;
    private String tags;
    private Double answerRate;
    private String problemUrl;
}
