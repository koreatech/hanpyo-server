package com.github.hanpyo.service;

import com.github.hanpyo.constant.LectureTestConstant;
import com.github.hanpyo.entity.Lecture;
import com.github.hanpyo.repository.LectureRepository;
import com.github.hanpyo.test.IntegrationTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

public class LectureServiceTest extends IntegrationTest {

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureService lectureService;

    @Test
    @DisplayName("DB에 있는 과목 List를 불러올 수 있다.")
    public void getDBLectureListTest() throws Exception {
        // given
        lectureRepository.saveAll(LectureTestConstant.mockLectureList);

        // when
        List<Lecture> lectureList =  lectureService.getLectures();
        Lecture targetLecture = lectureList.get(0);

        for (int i=0; i<lectureList.size(); i++) {
            System.out.println(lectureList.get(i).getId() instanceof Long);
        }

        // then
        then(LectureTestConstant.mockLecture1.getId()).isEqualTo(targetLecture.getId());
        then(lectureList.size()).isEqualTo(3);
    }
}
