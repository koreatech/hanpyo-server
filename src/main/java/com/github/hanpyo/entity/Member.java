package com.github.hanpyo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends AbstractBaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(name = "member_email", unique = true)
	private String email;

	@Column(name = "member_password")
	private String password;

	@Column(name = "member_name")
	private String name;

	@Column(name = "member_nickname")
	private String nickname;

	@Column(name = "member_grade")
	private Integer grade;

	@Column(name = "member_major")
	private String major;
}
