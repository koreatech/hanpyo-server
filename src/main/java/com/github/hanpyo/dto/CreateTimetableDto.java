package com.github.hanpyo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
@Builder
public class CreateTimetableDto {

    @NotNull
    private final String name;
}
