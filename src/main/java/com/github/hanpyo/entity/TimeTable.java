package com.github.hanpyo.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "time_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TimeTable extends AbstractBaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "time_table_id")
	private Long id;

	@Column(name = "time_table_name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "timeTable")
	private List<Cart> carts = new ArrayList<>();

	@OneToMany(mappedBy = "timeTable")
	private List<PersonalSchedule> personalSchedules = new ArrayList<>();
}
