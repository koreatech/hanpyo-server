package com.github.hanpyo.service;

import com.github.hanpyo.entity.Lecture;
import com.github.hanpyo.repository.LectureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Transactional
    public List<Lecture> getLectures() {
        return lectureRepository.findAll();
    }
}
