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
    public Lecture(Long id, String code, String name, String room, String professor, Integer credit, Integer requiredGrade, String requiredMajor, Integer totalStudentNumber, Integer currentStudentNumber, Integer divisionNumber, String department, List<LectureTime> lectureTimes) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.room = room;
        this.professor = professor;
        this.credit = credit;
        this.requiredGrade = requiredGrade;
        this.requiredMajor = requiredMajor;
        this.totalStudentNumber = totalStudentNumber;
        this.currentStudentNumber = currentStudentNumber;
        this.divisionNumber = divisionNumber;
        this.department = department;
        this.lectureTimes = lectureTimes;
    }

    public static Lecture from(Map<String, Object> lectureInfo) {
        Long id = Long.parseLong((String) lectureInfo.get("LECT_NO"));
        String code = (String) lectureInfo.get("CORS_CD");
        String name = (String) lectureInfo.get("CORS_NM");
        String room = "";
        if (lectureInfo.get("LECT_RM") != null)
            room = (String) lectureInfo.get("LECT_RM");
        String professor = (String) lectureInfo.get("PROF_NM");
        Integer credit = Integer.parseInt((String) lectureInfo.get("CREDIT"));
        Integer requiredGrade = Integer.parseInt((String) lectureInfo.get("SCH_YR"));
        String requiredMajor = "";
        Integer totalStudentNumber = 0;
        if (lectureInfo.get("CLS_CNT") != null)
            totalStudentNumber = Integer.parseInt((String) lectureInfo.get("CLS_CNT"));
        Integer currentStudentNumber = Integer.parseInt((String) lectureInfo.get("LECT_CNT"));
        Integer divisionNumber = Integer.parseInt((String) lectureInfo.get("CLS_NO"));
        String department = (String) lectureInfo.get("DEPT_NM");

        return Lecture.builder()
                .id(id)
                .code(code)
                .name(name)
                .room(room)
                .professor(professor)
                .credit(credit)
                .requiredGrade(requiredGrade)
                .requiredMajor(requiredMajor)
                .totalStudentNumber(totalStudentNumber)
                .currentStudentNumber(currentStudentNumber)
                .divisionNumber(divisionNumber)
                .department(department)
                .build();
    }

    public boolean equals(Lecture lecture) {
        if (lecture == null) return false;
        if (!this.id.equals(lecture.id)) return false;
        if (!this.code.equals(lecture.code)) return false;
        if (!this.name.equals(lecture.name)) return false;
        if (!this.room.equals(lecture.room)) return false;
        if (!this.professor.equals(lecture.professor)) return false;
        if (!this.credit.equals(lecture.credit)) return false;
        if (!this.requiredGrade.equals(lecture.requiredGrade)) return false;
        if (!this.requiredMajor.equals(lecture.requiredMajor)) return false;
        if (!this.totalStudentNumber.equals(lecture.totalStudentNumber)) return false;
        if (!this.currentStudentNumber.equals(lecture.currentStudentNumber)) return false;
        if (!this.divisionNumber.equals(lecture.divisionNumber)) return false;
        if (!this.department.equals(lecture.department)) return false;
//        if (this.lectureTimes != null && !this.lectureTimes.equals(lecture.lectureTimes)) return false;
        return true;
    }

    public void setLectureTimes(List<LectureTime> lectureTimes) {
        this.lectureTimes = lectureTimes;
    }
}
