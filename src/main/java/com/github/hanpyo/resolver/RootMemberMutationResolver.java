package com.github.hanpyo.resolver;

import com.github.hanpyo.dto.MemberDto;
import com.github.hanpyo.dto.SignUpDto;
import com.github.hanpyo.service.MemberService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@RequiredArgsConstructor
public class RootMemberMutationResolver implements GraphQLMutationResolver {

	private final MemberService memberService;

	@PreAuthorize("isAnonymous()")
	public MemberDto signUp(@Validated SignUpDto input) {
		return memberService.signUp(input);
	}
}
