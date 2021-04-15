package com.github.hanpyo.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

	ENTITY_NOT_FOUND("100", "entity not found"),
	ENTITY_EXISTS("101", "entity already exists"),
	WRONG_PASSWORD("200", "wrong password"),
	WRONG_SESSION("201", "wrong session");

	private final String codeNumber;
	private final String message;

	ErrorCode(String codeNumber, String message) {
		this.codeNumber = codeNumber;
		this.message = message;
	}
}
