package com.khk.backjoonrecommender.controller;

import com.khk.backjoonrecommender.entity.Rival;
import com.khk.backjoonrecommender.entity.User;
import com.khk.backjoonrecommender.repository.RivalRepository;
import com.khk.backjoonrecommender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class DataInit {


    private final UserRepository userRepository;
    private final RivalRepository rivalRepository;

//    @PostConstruct
    public void init() {
        User user1 = new User("seokju", "1234", "b1");
        User user2 = new User("jaejoon", "1234", "b2");
        User user3 = new User("nayeon", "1234", "b3");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        Rival rival1 = Rival.builder().selectingUser(user1).selectedUser(user2).build();
        Rival rival2 = Rival.builder().selectingUser(user1).selectedUser(user3).build();

        rival1.setUser(user1);
        rival2.setUser(user1);
        rivalRepository.save(rival1);
        rivalRepository.save(rival2);
    }
}
