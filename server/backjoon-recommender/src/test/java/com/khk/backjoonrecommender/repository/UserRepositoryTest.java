package com.khk.backjoonrecommender.repository;

import com.khk.backjoonrecommender.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Test
	void successToSave() {
		// given : 회원가입될 사용자 정보
		final String USERNAME = "kjj97";
		final String PASSWORD = "123";
		final String BAEKJOON_ID = "bj000";
		User user = User.builder()
				.baekJoonId(BAEKJOON_ID)
				.username(USERNAME)
				.password(PASSWORD)
				.build();
		userRepository.save(user);

		// when : DB 에서 아까 저장된 사용자 정보를 조회할 때
		User findUser = userRepository.findByUsername(USERNAME);

		// then : 동일한 username 을 가져야 한다.
		String findUsername = findUser.getUsername();
		assertThat(findUsername).isEqualTo(USERNAME);
	}

	@DisplayName("유니크 제약조건이 걸린 username field 에 대해서 중복된 username 이 저장되려고 할 때 실패해야 한다.")
	@Test
	void failToSave() {
		final String SAME_USERNAME = "kjj97";
		final String PASSWORD = "123";
		final String BAEKJOON_ID = "bj000";
		User user = new User(SAME_USERNAME, PASSWORD, BAEKJOON_ID);
		User sameUsernameUser = new User(SAME_USERNAME, PASSWORD, BAEKJOON_ID);

		userRepository.save(user);

		assertThatThrownBy(() -> userRepository.save(sameUsernameUser))
				.isInstanceOf(DataIntegrityViolationException.class);
	}
}