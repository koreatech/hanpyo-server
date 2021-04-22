package com.github.hanpyo.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public abstract class AbstractBusinessException extends RuntimeException implements GraphQLError {

	private ErrorCode errorCode;

	public AbstractBusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public AbstractBusinessException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorClassification getErrorType() {
		return null;
	}

	@Override
	public Map<String, Object> getExtensions() {
		Map<String, Object> extensions = new HashMap<>();
		extensions.put("errorCode", errorCode.getCodeNumber());
		return extensions;
	}
}
