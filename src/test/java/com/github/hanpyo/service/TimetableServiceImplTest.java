package com.github.hanpyo.service;

import com.github.hanpyo.dto.CreateTimetableDto;
import com.github.hanpyo.dto.TimetableDto;
import com.github.hanpyo.entity.Member;
import com.github.hanpyo.entity.MemberRole;
import com.github.hanpyo.repository.MemberRepository;
import com.github.hanpyo.repository.TimetableRepository;
import com.github.hanpyo.test.MockTest;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.anyLong;

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
        Member member = Member.builder()
                .email("hanpyo@koreatech.ac.kr")
                .name("hanpyolee")
                .nickname("hanpyo")
                .grade(1)
                .major("CSE")
                .role(MemberRole.VERIFIED_MEMBER)
                .build();

        BDDMockito.given(memberRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(member));

        memberRepository.save(member);

        Member myMember = memberRepository.findById(1L).get();

        CreateTimetableDto dto = CreateTimetableDto.builder()
                .name("Tab")
                .build();

        System.out.println(myMember.getId());
        // when
        TimetableDto result = timetableService.createTimetable(myMember.getId(), dto);

        // then
        then(result.getName()).isEqualTo(dto.getName());
    }
}