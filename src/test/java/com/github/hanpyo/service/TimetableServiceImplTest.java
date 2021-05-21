package com.github.hanpyo.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.lang.reflect.Field;
import java.util.Optional;

import com.github.hanpyo.dto.CreateTimetableDto;
import com.github.hanpyo.dto.TimetableDto;
import com.github.hanpyo.entity.Member;
import com.github.hanpyo.entity.MemberRole;
import com.github.hanpyo.entity.TimeTable;
import com.github.hanpyo.repository.MemberRepository;
import com.github.hanpyo.repository.TimetableRepository;
import com.github.hanpyo.test.MockTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TimetableServiceImplTest extends MockTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private TimetableRepository timetableRepository;

    @InjectMocks
    private TimetableServiceImpl timetableService;

    @Test
    public void postTimetableThenReturnTimetableDto() {
        // given
        long givenMemberId = 1L;
        final Member member = Member.builder()
            .email("hanpyo@koreatech.ac.kr")
            .name("hanpyolee")
            .nickname("hanpyo")
            .grade(1)
            .major("CSE")
            .role(MemberRole.VERIFIED_MEMBER)
            .build();

        given(memberRepository.findById(eq(givenMemberId)))
            .willAnswer(invocation -> {
                // Set a field by reflection
                long memberId = invocation.getArgument(0, Long.class);
                Field idField = Member.class.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(member, givenMemberId);
                return Optional.of(member);
            });

        CreateTimetableDto dto = CreateTimetableDto.builder()
            .name("Tab")
            .build();

        long givenTimeTableId = 2L;
        given(timetableRepository.save(any())).willAnswer(invocation -> {
            // Set a field by reflection
            TimeTable reflectedTimeTable = invocation.getArgument(0, TimeTable.class);
            Field idField = TimeTable.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(reflectedTimeTable, givenTimeTableId);
            return reflectedTimeTable;
        });

        // when
        TimetableDto result = timetableService.createTimetable(givenMemberId, dto);

        // then
        then(result.getName()).isEqualTo(dto.getName());
    }
}
