package com.github.hanpyo.service;

import com.github.hanpyo.entity.Lecture;
import com.github.hanpyo.repository.LectureRepository;
import com.github.hanpyo.test.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

public class LectureGetterServiceTest extends IntegrationTest {

    @Autowired
    private LectureGetterService lectureGetterService;

    @Autowired
    private LectureRepository lectureRepository;


    @Test
    @DisplayName("Document를 List로 변환 가능해야 한다.")
    public void getLectureListTest() throws Exception {
        List<Map<String, Object>> lectures = lectureGetterService.getLectureList();
        System.out.println(lectures);
        then(lectures instanceof List).isEqualTo(true);
    }
}
