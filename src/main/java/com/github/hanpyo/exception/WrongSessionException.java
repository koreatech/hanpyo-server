package com.github.hanpyo.exception;

public class WrongSessionException extends AbstractBusinessException {

	public WrongSessionException() {
		super(ErrorCode.WRONG_SESSION);
	}

	public WrongSessionException(String message) {
		super(message, ErrorCode.WRONG_SESSION);
	}
}
