package com.github.hanpyo.dto;

import com.github.hanpyo.entity.MemberRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberDto {

	private final Long id;
	private final String email;
	private final String name;
	private final String nickname;
	private final Integer grade;
	private final String major;
	private final MemberRole role;
}
