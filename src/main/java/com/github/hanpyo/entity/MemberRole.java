package com.github.hanpyo.entity;

import lombok.Getter;

@Getter
public enum MemberRole {

	VERIFIED_MEMBER("ROLE_VERIFIED_MEMBER"), UNVERIFIED_MEMBER("ROLE_UNVERIFIED_MEMBER");

	private String role;

	MemberRole(String role) {
		this.role = role;
	}
}
