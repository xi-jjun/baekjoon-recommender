package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.Rival;
import com.khk.backjoonrecommender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RivalRepository extends JpaRepository<Rival, Long> {

    List<Rival> findAllBySelectingUser(User selectingUser);

    @Query("SELECT s FROM Rival s WHERE s.selectedUser.id = :id")
    List<Rival> findSelectedUserById(@Param("id") Long id);
}
