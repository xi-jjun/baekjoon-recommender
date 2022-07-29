package com.khk.backjoonrecommender.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khk.backjoonrecommender.controller.dto.request.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private static final int INIT_REFRESH_COUNT = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String baekJoonId;

    private int refreshCount;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "selectedUser")
    private List<Rival> rivals = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "setting_id")
    private Setting setting;

    @JsonIgnore
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

    public void updateUserInfo(UserRequestDto userRequestDTO) {
        this.baekJoonId = userRequestDTO.getBaekJoonId();
        this.password = userRequestDTO.getPassword();
    }

    public boolean hasNoRefreshCount() {
        return this.refreshCount <= 0;
    }

    public void decreaseReloadCount() {
        if (this.refreshCount > 0) {
            --this.refreshCount;
        }
    }

    public void resetRefreshCount() {
        this.refreshCount = INIT_REFRESH_COUNT;
    }
}
