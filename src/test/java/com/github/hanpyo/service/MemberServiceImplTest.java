package com.github.hanpyo.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

import java.util.Optional;

import com.github.hanpyo.dto.MemberDto;
import com.github.hanpyo.entity.Member;
import com.github.hanpyo.entity.MemberRole;
import com.github.hanpyo.exception.EntityNotFoundException;
import com.github.hanpyo.mapper.MemberMapper;
import com.github.hanpyo.repository.MemberRepository;
import com.github.hanpyo.test.MockTest;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberServiceImplTest extends MockTest {

	@Mock
	private MemberRepository memberRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private MemberMapper memberMapper;

	@InjectMocks
	private MemberServiceImpl memberService;

	@Test
	public void getMemberByIdThenThrowsEntityNotFoundException() {
		// given
		BDDMockito.given(memberRepository.findById(anyLong()))
			.willReturn(Optional.empty());

		// when
		Throwable throwable = catchThrowable(() -> {
			memberService.getMemberById(1L);
		});

		// then
		BDDAssertions.then(throwable).isInstanceOf(EntityNotFoundException.class);
	}

	@Test
	public void getMemberByIdThenReturnsMemberDto() {
		// given
		Member returnMember = Member.builder()
			.email("hanpyo@gmail.com")
			.name("hanpyolee")
			.nickname("hanpyo")
			.grade(1)
			.major("CSE")
			.role(MemberRole.VERIFIED_MEMBER)
			.build();
		BDDMockito.given(memberRepository.findById(anyLong()))
			.willReturn(Optional.ofNullable(returnMember));

		// when
		MemberDto result = memberService.getMemberById(1L);

		// then
		ArgumentCaptor<Member> memberCaptor = ArgumentCaptor.forClass(Member.class);
		BDDMockito.then(memberMapper).should().toMemberDto(memberCaptor.capture());
		Member captedMember = memberCaptor.getAllValues().get(0);
		BDDAssertions.then(captedMember).isNotNull();
		BDDAssertions.then(captedMember).isEqualTo(returnMember);
	}
}
