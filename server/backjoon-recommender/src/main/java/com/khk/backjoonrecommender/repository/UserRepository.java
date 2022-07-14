package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	List<User> findByBaekJoonId(String baekJoonId);
}
