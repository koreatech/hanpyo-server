package com.github.hanpyo.repository;

import static com.github.hanpyo.entity.QMember.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public boolean existsByEmail(String email) {
		Integer one = jpaQueryFactory.selectOne()
			.from(member)
			.where(member.email.eq(email))
			.fetchOne();
		return one != null;
	}

	@Override
	public boolean existsByNickname(String nickname) {
		Integer one = jpaQueryFactory.selectOne()
			.from(member)
			.where(member.nickname.eq(nickname))
			.fetchOne();
		return one != null;
	}
}
