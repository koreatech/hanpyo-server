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

import com.google.common.base.Objects;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "lecture_time")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LectureTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lecture_time_id")
	private Long id;

	@Column(name = "lecture_time_start_time")
	private Integer startTime;

	@Column(name = "lecture_time_end_time")
	private Integer endTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lecture_id")
	private Lecture lecture;

	@Builder
	public LectureTime(Integer startTime, Integer endTime, Lecture lecture) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.lecture = lecture;
	}

	public static LectureTime from (String time, Lecture lecture) {
		Map<String, Integer> days = new HashMap<>() {{
			put("월", 540);
			put("화", 1980);
			put("수", 3420);
			put("목", 4860);
			put("금", 6300);
			put("토", 7740);
			put("일", 9180);
		}};

		Map<String, Integer> alphas = new HashMap<>() {{
			put("A", 0);
			put("B", 30);
		}};

		Integer dayTime = days.get(time.substring(0, 1));
		Integer sTime = (Integer.parseInt(time.substring(1, 3)) - 1) * 60 + alphas.get(time.substring(3, 4));
		Integer eTime = (Integer.parseInt(time.substring(5, 7)) -1) * 60 + alphas.get(time.substring(7, 8)) + 30;

		Integer startTime = dayTime + sTime;
		Integer endTime = dayTime + eTime;

		return LectureTime.builder()
				.startTime(startTime)
				.endTime(endTime)
				.lecture(lecture)
				.build();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LectureTime that = (LectureTime) o;
		return Objects.equal(id, that.id) && Objects.equal(startTime, that.startTime) && Objects.equal(endTime, that.endTime) && Objects.equal(lecture, that.lecture);
	}
}
