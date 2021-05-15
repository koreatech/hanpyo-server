package com.github.hanpyo.resolver;

import com.github.hanpyo.config.security.MemberDetails;
import com.github.hanpyo.dto.MemberDto;
import com.github.hanpyo.exception.WrongSessionException;
import com.github.hanpyo.repository.MemberRepository;
import com.github.hanpyo.service.LectureService;
import com.github.hanpyo.service.MemberService;

import graphql.kickstart.tools.GraphQLQueryResolver;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RootMemberQueryResolver implements GraphQLQueryResolver {

	private final MemberService memberService;
	private final MemberRepository memberRepository;
	private final LectureService lectureService;

	@PreAuthorize("hasRole('VERIFIED_MEMBER')")
	public MemberDto myMemberInfo() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(principal instanceof MemberDetails)) {
			throw new WrongSessionException();
		}
		MemberDetails memberDetails = (MemberDetails)principal;
		return memberService.getMemberById(memberDetails.getId());
	}

	public boolean memberDuplicatedByEmail(String email) {
		return memberRepository.existsByEmail(email);
	}

	public boolean memberDuplicatedByNickname(String nickname) {
		return memberRepository.existsByNickname(nickname);
	}

}
