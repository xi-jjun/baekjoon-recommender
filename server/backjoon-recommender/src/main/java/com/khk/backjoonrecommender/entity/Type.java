package com.khk.backjoonrecommender.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Type {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<ProblemType> problemTypes;

}
