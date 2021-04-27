package com.github.hanpyo.repository;

public interface CustomMemberRepository {

	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);
}
