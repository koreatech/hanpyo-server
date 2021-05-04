package com.github.hanpyo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
public class LectureTime {
    private final Integer start;
    private final Integer end;

    @ConstructorProperties({"start", "end"})
    public LectureTime(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }
}
