package com.khk.backjoonrecommender.entity;

import javax.persistence.*;

@Entity
public class Setting {

    @Id
    @GeneratedValue
    private Long Id;

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
