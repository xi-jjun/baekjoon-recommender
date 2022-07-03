package com.khk.backjoonrecommender.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Table(name = "Users")
@Entity
public class User {
    private final int INIT_RELOAD_COUNT = 3;

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String baekJoonId;

    private int reloadCount;

    @OneToOne
    @JoinColumn(name = "setting_id")
    private Setting setting;

    public void setProblemFilterSetting(Setting setting) {
        this.setting = setting;
    }

    public User(String username, String password, String baekJoonId) {
        this.username = username;
        this.password = password;
        this.baekJoonId = baekJoonId;
    }

    public void resetReloadCount() {
        this.reloadCount = INIT_RELOAD_COUNT;
    }
}
