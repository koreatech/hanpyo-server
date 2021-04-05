package com.github.hanpyo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
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

	@Column(name = "lecture_division_number")
	private Integer divisionNumber;

	@Column(name = "lecture_department")
	private String department;

	@OneToMany(mappedBy = "lecture")
	private List<LectureTime> lectureTimes = new ArrayList<>();

	@Builder
	public Lecture(Map<String, Object> lectureInfo) {
		this.id = Long.parseLong((String) lectureInfo.get("LECT_NO"));
		this.code = (String) lectureInfo.get("CORS_CD");
		this.name = (String) lectureInfo.get("CORS_NM");
		this.room = (String) lectureInfo.get("LECT_RM");
		this.professor = (String) lectureInfo.get("PROF_NM");
		this.credit = Integer.parseInt((String) lectureInfo.get("CREDIT"));
		this.requiredGrade = Integer.parseInt((String) lectureInfo.get("SCH_YR"));
		this.requiredMajor = "";
		if (lectureInfo.get("CLS_CNT") == null) this.totalStudentNumber = 0;
		else this.totalStudentNumber = Integer.parseInt((String) lectureInfo.get("CLS_CNT"));
		this.currentStudentNumber = Integer.parseInt((String) lectureInfo.get("LECT_CNT"));
		this.divisionNumber = Integer.parseInt((String) lectureInfo.get("CLS_NO"));
		this.department = (String) lectureInfo.get("DEPT_NM");
	}
}
