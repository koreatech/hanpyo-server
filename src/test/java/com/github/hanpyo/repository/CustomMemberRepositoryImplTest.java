package com.github.hanpyo.repository;

import static org.assertj.core.api.BDDAssertions.*;

import com.github.hanpyo.entity.Member;
import com.github.hanpyo.test.JpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomMemberRepositoryImplTest extends JpaTest {

	@Autowired
	private CustomMemberRepositoryImpl customMemberRepository;

	@Test
	public void existsByEmailThenReturnsFalse() {
		// given
		String givenEmail = "youngxpepp@gmail.com";

		// when
		boolean result = customMemberRepository.existsByEmail(givenEmail);

		// then
		then(result).isFalse();
	}

	@Test
	public void existsByEmailThenReturnsTrue() {
		// given
		Member existMember = Member.builder()
			.email("youngxpepp@gmail.com")
			.build();
		em.persist(existMember);

		em.flush();
		em.clear();

		// when
		boolean result = customMemberRepository.existsByEmail(existMember.getEmail());

		// then
		then(result).isTrue();
	}

	@Test
	public void existsByNicknameThenReturnsFalse() {
		// given
		String givenNickname = "youngxpepp";

		// when
		boolean result = customMemberRepository.existsByNickname(givenNickname);

		// then
		then(result).isFalse();
	}

	@Test
	public void existsByNicknameThenReturnsTrue() {
		// given
		Member existMember = Member.builder()
			.nickname("youngxpepp")
			.build();
		em.persist(existMember);

		em.flush();
		em.clear();

		// when
		boolean result = customMemberRepository.existsByNickname(existMember.getNickname());

		// then
		then(result).isTrue();
	}
}
