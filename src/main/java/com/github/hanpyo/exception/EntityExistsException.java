package com.github.hanpyo.exception;

public class EntityExistsException extends AbstractBusinessException {

	public EntityExistsException() {
		super(ErrorCode.ENTITY_EXISTS);
	}

	public EntityExistsException(String message) {
		super(message, ErrorCode.ENTITY_EXISTS);
	}
}
