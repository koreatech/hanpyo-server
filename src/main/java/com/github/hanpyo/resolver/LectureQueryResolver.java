package com.github.hanpyo.resolver;

import com.github.hanpyo.dto.LectureDto;
import com.github.hanpyo.entity.Lecture;
import com.github.hanpyo.service.LectureService;

import graphql.kickstart.tools.GraphQLQueryResolver;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureQueryResolver implements GraphQLQueryResolver {

    private final LectureService lectureService;

    public List<LectureDto> getLectureInfos() {
        final List<Lecture> all = lectureService.getLectures();
        return LectureDto.from(all);
    }
}
