package com.github.hanpyo.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lecture")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Lecture extends AbstractBaseTimeEntity {

	@Id
	@Column(name = "lecture_id")
	private Long id;

	@Column(name = "lecture_code")
	private String code;

	@Column(name = "lecture_name")
	private String name;

	@Column(name = "lecture_room")
	private String room;

	@Column(name = "lecture_professor")
	private String professor;

	@Column(name = "lecture_credit")
	private Integer credit;

	@Column(name = "lecture_required_grade")
	private Integer requiredGrade;

	@Column(name = "lecture_required_major")
	private String requiredMajor;

	@Column(name = "lecture_total_student_number")
	private Integer totalStudentNumber;

	@Column(name = "lecture_current_student_number")
	private Integer currentStudentNumber;

	@Column(name = "lecture_divistion_number")
	private Integer divisionNumber;

	@Column(name = "lecture_department")
	private String department;

	@OneToMany(mappedBy = "lecture")
	private List<LectureTime> lectureTimes = new ArrayList<>();
}
