package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.Option;
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
		final String LEVEL = "1,2,3,4,5,6,7";
		final String TAGS = "DFS,BFS,DP,BINARY SEARCH";
		User user = userRepository.findByUsername("kjj97");

		Setting setting = Setting.builder()
				.option(Option.TODAY)
				.levels(LEVEL)
				.tags(TAGS)
				.build();

		user.setProblemFilterSetting(setting);  // 사용자와 setting 연관 매핑 해준다.

		// setting 정보를 저장 (User 와 매핑된 상태로)
		settingRepository.save(setting);
		em.flush(); // 이 때 user 에 setting 정보가 매핑 됐기에 dirty check 발생
		em.clear();

		// 사용자 정보를 DB 에서 꺼내온 후, Setting 에 관한 정보 조회
		User findUser = userRepository.findByUsername("kjj97");
		Setting userSetting = findUser.getSetting();

		assertThat(userSetting.getOption()).isEqualTo(Option.TODAY);
		assertThat(userSetting.getLevels()).isEqualTo(LEVEL);
		assertThat(userSetting.getTags()).isEqualTo(TAGS);
	}
}