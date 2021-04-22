package com.github.hanpyo.repository;

import java.util.Optional;

import com.github.hanpyo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByEmail(String email);

	Optional<Member> findByNickname(String email);
}
