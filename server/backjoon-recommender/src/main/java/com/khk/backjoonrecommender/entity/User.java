package com.khk.backjoonrecommender.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "Users")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String baekJoonId;

    private int reloadCount;

    @OneToMany(mappedBy = "user")
    private List<SolvedProblem> solvedProblemList = new ArrayList<>();

    public User(String username, String password, String baekJoonId) {
        this.username = username;
        this.password = password;
        this.baekJoonId = baekJoonId;
    }
}
