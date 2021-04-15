package com.github.hanpyo.exception;

public class EntityNotFoundException extends AbstractBusinessException {

	public EntityNotFoundException() {
		super(ErrorCode.ENTITY_NOT_FOUND);
	}

	public EntityNotFoundException(String message) {
		super(message, ErrorCode.ENTITY_NOT_FOUND);
	}
}
