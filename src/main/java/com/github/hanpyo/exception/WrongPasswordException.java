package com.github.hanpyo.exception;

public class WrongPasswordException extends AbstractBusinessException {

	public WrongPasswordException() {
		super(ErrorCode.WRONG_PASSWORD);
	}

	public WrongPasswordException(String message) {
		super(message, ErrorCode.WRONG_PASSWORD);
	}
}
