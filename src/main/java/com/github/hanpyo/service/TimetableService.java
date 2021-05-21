package com.github.hanpyo.service;

import com.github.hanpyo.dto.CreateTimetableDto;
import com.github.hanpyo.dto.TimetableDto;

public interface TimetableService {

    TimetableDto createTimetable(long memberId, CreateTimetableDto dto);
}
