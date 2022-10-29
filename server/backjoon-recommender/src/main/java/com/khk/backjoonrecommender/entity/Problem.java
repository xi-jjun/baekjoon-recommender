package com.khk.backjoonrecommender.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Problem {

    @Id
    @Column(name = "problem_id")
    private Long id;

    private String title;

    @Column(name = "problem_lv")
    private int level;

    @JsonIgnore
    @OneToMany(mappedBy = "problem")
    private List<ProblemTag> problemTags;
}
