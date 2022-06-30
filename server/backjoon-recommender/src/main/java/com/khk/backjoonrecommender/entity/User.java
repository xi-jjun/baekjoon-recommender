package com.khk.backjoonrecommender.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public User(String username, String password, String baekJoonId) {
        this.username = username;
        this.password = password;
        this.baekJoonId = baekJoonId;
    }
}
