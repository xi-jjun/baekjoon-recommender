package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.Setting;
import com.khk.backjoonrecommender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Long> {
	Setting findByUser(User user);
}
