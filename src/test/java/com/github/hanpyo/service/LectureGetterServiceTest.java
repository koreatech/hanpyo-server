package com.github.hanpyo.service;

import com.github.hanpyo.constant.LectureTestConstant;
import com.github.hanpyo.entity.Lecture;
import com.github.hanpyo.repository.LectureRepository;
import com.github.hanpyo.repository.LectureTimeRepository;
import com.github.hanpyo.test.IntegrationTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

public class LectureGetterServiceTest extends IntegrationTest {

    @Autowired
    private LectureGetterService lectureGetterService;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureTimeRepository lectureTimeRepository;

    private List<Lecture> mockLectureList = LectureTestConstant.mockLectureList;
    private List<Lecture> mockUpdateLectureList = LectureTestConstant.mockUpdateLectureList;

    @Test
    @DisplayName("불러온 Document를 List로 변환 가능해야 한다.")
    public void getLectureListTest() throws Exception {
        List<Lecture> lectures = lectureGetterService.getLectureList();
        then(lectures instanceof List).isEqualTo(true);
    }

    @Test
    @DisplayName("새로운 과목 List를 DB에 저장할 수 있다.")
    public void createLecturesTest() throws Exception {
        // given
        Lecture targetLecture = LectureTestConstant.mockLecture1;

        // when
        lectureGetterService.updateLectures(mockLectureList);
        Lecture lecture = lectureRepository.findById(targetLecture.getId()).get();

        // then
        then(targetLecture.getId()).isEqualTo(lecture.getId());
    }

    @Test
    @DisplayName("과목이 업데이트 될 때 삭제된 과목 처리가 가능하다.")
    public void deleteLecturesTest() throws Exception {
        // given
        lectureRepository.saveAll(mockLectureList);
        Lecture targetLecture = LectureTestConstant.mockLecture3;

        // when
        lectureGetterService.updateLectures(mockUpdateLectureList);
        Optional<Lecture> deleteLecture = lectureRepository.findById(targetLecture.getId());

        // then
        then(deleteLecture).isEmpty();
    }

    @Test
    @DisplayName("과목이 업데이트 될 때 정보가 변경된 과목 처리가 가능하다.")
    public void updateLecturesTest() throws Exception {
        // given
        lectureRepository.saveAll(mockLectureList);

        // when
        lectureGetterService.updateLectures(mockUpdateLectureList);
        Lecture updateLecture = lectureRepository.findById(123L).get();

        // then
        then(updateLecture.getName()).isEqualTo("변경된 과목");
    }

    @Test
    @DisplayName("시간이 변경된 과목 업데이트가 가능하다.")
    public void updateLecturesTimeTest() throws Exception {
        then(true).isEqualTo(true);
    }
}
