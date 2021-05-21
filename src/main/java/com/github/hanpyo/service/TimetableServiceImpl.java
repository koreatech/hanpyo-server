package com.github.hanpyo.service;

import com.github.hanpyo.dto.CreateTimetableDto;
import com.github.hanpyo.dto.TimetableDto;
import com.github.hanpyo.entity.Member;
import com.github.hanpyo.entity.TimeTable;
import com.github.hanpyo.exception.EntityNotFoundException;
import com.github.hanpyo.repository.MemberRepository;
import com.github.hanpyo.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final MemberRepository memberRepository;
    private final TimetableRepository timetableRepository;

    @Override
    @Transactional
    public TimetableDto createTimetable(long memberId, CreateTimetableDto dto) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(EntityNotFoundException::new);

        TimeTable timeTable = TimeTable.builder()
                .name(dto.getName())
                .member(member)
                .build();
        timetableRepository.save(timeTable);

        return TimetableDto.builder()
                .id(timeTable.getId())
                .name(timeTable.getName())
                .build();
    }
}
