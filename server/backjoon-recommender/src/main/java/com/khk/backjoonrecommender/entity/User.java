package com.khk.backjoonrecommender.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Users")
@Entity
public class User {
    @Transient
    private static final int INIT_RELOAD_COUNT = 3;

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String baekJoonId;

    private int reloadCount;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "selectedUser")
    private List<Rival> rivals = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "setting_id")
    private Setting setting;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<TriedProblem> triedProblemList = new ArrayList<>();

    public void setProblemFilterSetting(Setting setting) {
        this.setting = setting;
    }

    public User(String username, String password, String baekJoonId) {
        this.username = username;
        this.password = password;
        this.baekJoonId = baekJoonId;
    }

    public void decreaseReloadCount() {
        if (this.reloadCount > 0) {
            --this.reloadCount;
        }
    }

    public void resetReloadCount() {
        this.reloadCount = INIT_RELOAD_COUNT;
    }
}
