package com.github.hanpyo.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SignUpDto {

	@NotNull
	private final String email;

	@NotNull
	private final String password;

	@NotNull
	private final String name;

	@NotNull
	private final String nickname;

	@NotNull
	private final Integer grade;

	@NotNull
	private final String major;
}
