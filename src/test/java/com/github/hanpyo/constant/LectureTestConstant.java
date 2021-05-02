package com.github.hanpyo.constant;

import com.github.hanpyo.entity.Lecture;

import java.util.Arrays;
import java.util.List;

public class LectureTestConstant {
    public static Lecture mockLecture1 = Lecture.builder()
            .id(123L)
            .code("")
            .name("")
            .room("")
            .professor("")
            .credit(3)
            .requiredGrade(3)
            .requiredMajor("")
            .totalStudentNumber(30)
            .currentStudentNumber(30)
            .divisionNumber(1)
            .department("")
            .lectureTimes("[{start:3540, end:3660}, {start:3720, end:3840}]")
            .build();

    public static Lecture mockLecture2 = Lecture.builder()
            .id(124L)
            .code("")
            .name("")
            .room("")
            .professor("")
            .credit(3)
            .requiredGrade(3)
            .requiredMajor("")
            .totalStudentNumber(30)
            .currentStudentNumber(30)
            .divisionNumber(1)
            .department("")
            .lectureTimes("[{start:3540, end:3660}, {start:3720, end:3840}]")
            .build();

    public static Lecture mockLecture3 = Lecture.builder()
            .id(125L)
            .code("")
            .name("DELETE")
            .room("")
            .professor("")
            .credit(3)
            .requiredGrade(3)
            .requiredMajor("")
            .totalStudentNumber(30)
            .currentStudentNumber(30)
            .divisionNumber(1)
            .department("")
            .lectureTimes("[{start:3540, end:3660}, {start:3720, end:3840}]")
            .build();

    public static Lecture mockLecture4 = Lecture.builder()
            .id(123L)
            .code("")
            .name("변경된 과목")
            .room("")
            .professor("")
            .credit(3)
            .requiredGrade(3)
            .requiredMajor("")
            .totalStudentNumber(30)
            .currentStudentNumber(30)
            .divisionNumber(1)
            .department("")
            .lectureTimes("[{start:3540, end:3660}, {start:3720, end:3840}]")
            .build();

    public static Lecture mockLecture5 = Lecture.builder()
            .id(124L)
            .code("")
            .name("")
            .room("")
            .professor("")
            .credit(3)
            .requiredGrade(3)
            .requiredMajor("")
            .totalStudentNumber(30)
            .currentStudentNumber(30)
            .divisionNumber(1)
            .department("")
            .lectureTimes("[{start:3540, end:3660}]")
            .build();

    public static List<Lecture> mockLectureList = Arrays.asList(mockLecture1, mockLecture2, mockLecture3);
    public static List<Lecture> mockUpdateLectureList = Arrays.asList(mockLecture2, mockLecture4);
    public static List<Lecture> mockUpdateLectureTimeList = Arrays.asList(mockLecture1, mockLecture5, mockLecture3);
}
