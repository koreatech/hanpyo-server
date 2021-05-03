package com.github.hanpyo.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hanpyo.entity.Lecture;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Builder
@Getter
public class LectureDto {

    private final Long id;
    private final String code;
    private final String name;
    private final String room;
    private final String professor;
    private final Integer credit;
    private final Integer requiredGrade;
    private final String requiredMajor;
    private final Integer totalStudentNumber;
    private final Integer currentStudentNumber;
    private final Integer divisionNumber;
    private final String department;
    private final LectureTime[] lectureTimes;

    public static List<LectureDto> from(Collection<Lecture> entities) {
        return entities.stream().map(LectureDto::from).collect(Collectors.toList());
    }

    public static LectureDto from(Lecture lecture) {
        LectureTime[] lectureArray = parseStringToArrayObject(lecture.getLectureTimes());

        return LectureDto.builder()
                .id(lecture.getId())
                .code(lecture.getCode())
                .name(lecture.getName())
                .room(lecture.getRoom())
                .professor(lecture.getProfessor())
                .credit(lecture.getCredit())
                .requiredGrade(lecture.getRequiredGrade())
                .requiredMajor(lecture.getRequiredMajor())
                .totalStudentNumber(lecture.getTotalStudentNumber())
                .currentStudentNumber(lecture.getCurrentStudentNumber())
                .divisionNumber(lecture.getDivisionNumber())
                .department(lecture.getDepartment())
                .lectureTimes(lectureArray)
                .build();
    }

    public static LectureTime[] parseStringToArrayObject(String lectureTimeArray) {
        if (lectureTimeArray == "") return null;
        ObjectMapper objectMapper = new ObjectMapper();
        LectureTime[] lectureTimes = null;
        try {
            lectureTimes = objectMapper.readValue(lectureTimeArray, LectureTime[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return lectureTimes;
    }
}
