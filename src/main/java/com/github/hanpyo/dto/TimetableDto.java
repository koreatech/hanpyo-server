package com.github.hanpyo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class TimetableDto {

    private final Long id;
    private final String name;
}
