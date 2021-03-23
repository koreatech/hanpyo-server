package com.github.hanpyo.entity;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractBaseTimeEntity {

	@CreatedDate
	protected LocalDateTime createdAt;

	@LastModifiedDate
	protected LocalDateTime updatedAt;
}
