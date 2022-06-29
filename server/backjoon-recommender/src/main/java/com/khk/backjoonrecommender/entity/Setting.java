package com.khk.backjoonrecommender.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter // for test
@Entity
public class Setting {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    private String level;
    private String problemType;

    @Enumerated(value = EnumType.STRING)
    private OfferedType offeredType;

    private String sun;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
}
