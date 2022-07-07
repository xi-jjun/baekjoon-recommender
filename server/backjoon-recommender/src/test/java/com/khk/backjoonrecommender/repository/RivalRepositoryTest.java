package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.Rival;
import com.khk.backjoonrecommender.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RivalRepositoryTest {

    @Autowired
    private RivalRepository rivalRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void addUser() {
        User user1 = new User("seokju", "1234", "b1");
        User user2 = new User("jaejoon", "1234", "b2");
        User user3 = new User("nayeon", "1234", "b3");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    @Test
    @DisplayName("라이벌 한 명 테이블 넣고 선택된 유저 맞는지 확인")
    void insertRival() {

        // given
        User selectingUser = userRepository.findByUsername("seokju");
        User selectedUser1 = userRepository.findByUsername("jaejoon");


        // when
        Rival rival = Rival.builder().selectingUser(selectingUser).selectedUser(selectedUser1).build();
        rival.setUser(selectingUser);
        Rival savedRival = rivalRepository.save(rival);

        // then
        System.out.println(savedRival.getSelectingUser());
        System.out.println(selectingUser);
        Assertions.assertThat(savedRival.getSelectingUser()).isSameAs(selectingUser);
    }

    @Test
    @DisplayName("라이벌 테이블 두 명 넣고 확인")
    void insertRivals() {

        // given
        User selectingUser = userRepository.findByUsername("seokju");
        User selectedUser1 = userRepository.findByUsername("jaejoon");
        User selectedUser2 = userRepository.findByUsername("nayeon");

        // when
        Rival rival1 = Rival.builder().selectingUser(selectingUser).selectedUser(selectedUser1).build();
        Rival rival2 = Rival.builder().selectingUser(selectingUser).selectedUser(selectedUser2).build();
        rival1.setUser(selectingUser);
        rival2.setUser(selectingUser);
        rivalRepository.save(rival1);
        rivalRepository.save(rival2);

        List<Rival> rivals = selectingUser.getRivals();
        System.out.println(rivals);
        Assertions.assertThat(rivals.size()).isEqualTo(2);
    }

}