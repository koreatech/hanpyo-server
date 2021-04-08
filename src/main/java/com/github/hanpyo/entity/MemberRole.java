package com.github.hanpyo.entity;

import lombok.Getter;

@Getter
public enum MemberRole {

	MEMBER("ROLE_MEMBER"), UNAUTHENTICATED("ROLE_UNAUTHENTICATED");

	private String role;

	MemberRole(String role) {
		this.role = role;
	}
}
