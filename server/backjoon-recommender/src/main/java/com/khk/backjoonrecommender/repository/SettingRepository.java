package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Long> {
}
