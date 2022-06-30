package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.OfferedType;
import com.khk.backjoonrecommender.entity.Setting;
import com.khk.backjoonrecommender.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class SettingRepositoryTest {
	@Autowired
	private EntityManager em;

	@Autowired
	private SettingRepository settingRepository;

	@Autowired
	private UserRepository userRepository;

	@BeforeAll
	void createUser() {
		final String USERNAME = "kjj97";
		final String PASSWORD = "123";
		final String BAEKJOON_ID = "bj000";
		User user = new User(USERNAME, PASSWORD, BAEKJOON_ID);
		userRepository.save(user);
	}

	@Test
	void saveSettingOptionToday() {
		// given
		final String LEVEL = "1, 2, 3, 4, 5, 6, 7";
		final String TYPE = "DFS, BFS, DP, BINARY SEARCH";
		User user = userRepository.findByUsername("kjj97");

		Setting setting = new Setting();
		setting.setUser(user);
		setting.setOfferedType(OfferedType.TODAY);

		// TODAY 에 해당하는 사용자 맞춤 문제추천 필터 설정
		setting.setProblemType(TYPE);
		setting.setLevel(LEVEL);

		settingRepository.save(setting);
		em.flush();
		em.clear();

		// when
		User findUser = userRepository.findByUsername("kjj97");
		Setting findUserSetting = settingRepository.findByUser(findUser);


		// then
		assertThat(findUserSetting.getUser().getBaekJoonId()).isEqualTo("bj000");
		assertThat(findUserSetting.getOfferedType()).isEqualTo(OfferedType.TODAY);
	}
}