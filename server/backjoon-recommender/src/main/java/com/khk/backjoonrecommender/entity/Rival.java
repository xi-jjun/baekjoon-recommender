package com.khk.backjoonrecommender.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Rival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User selectingUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private User selectedUser;

    @Builder
    public Rival(User selectingUser, User selectedUser) {
        this.selectingUser = selectingUser;
        this.selectedUser = selectedUser;
    }

    @Override
    public String toString() {
        return selectingUser.getUsername() + "'s rival = " + selectedUser.getUsername();
    }

    public void setUser(User user) {
        if (this.selectingUser != null) {
            this.selectingUser.getRivals().remove(this);
        }
        this.selectingUser = user;
        user.getRivals().add(this);
    }
}
