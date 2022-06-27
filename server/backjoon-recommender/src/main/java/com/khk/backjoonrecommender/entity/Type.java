package com.khk.backjoonrecommender.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Type {

    @javax.persistence.Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<ProblemType> problemTypes;

}
