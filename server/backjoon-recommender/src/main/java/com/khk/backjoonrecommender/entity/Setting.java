package com.khk.backjoonrecommender.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String levels;
    private String tags;

    @Enumerated(value = EnumType.STRING)
    private Option option;

    private String sun;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
}
