package com.github.hanpyo.util;

import com.github.hanpyo.entity.Lecture;
import com.github.hanpyo.service.LectureGetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CronScheduler {
    @Autowired
    private LectureGetterService lectureGetterService;

    private final int updateLectureTime = 300000;

    @Scheduled(fixedDelay = updateLectureTime)
    public void cronJobSch() throws Exception {
        setLecture();
    }

    public void setLecture() throws Exception {
        List<Lecture> lectures = lectureGetterService.getLectureList();
        lectureGetterService.updateLectures(lectures);
    }
}
