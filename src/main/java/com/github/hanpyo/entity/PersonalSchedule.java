package com.github.hanpyo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personal_schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PersonalSchedule extends AbstractBaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "personal_schedule_id")
	private Long id;

	@Column(name = "personal_schedule_start_time")
	private Integer startTime;

	@Column(name = "personal_schedule_end_time")
	private Integer endTime;

	@Column(name = "personal_schedule_name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_table_id")
	private TimeTable timeTable;
}
