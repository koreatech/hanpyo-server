package com.github.hanpyo.service;

import com.github.hanpyo.dto.MemberDto;
import com.github.hanpyo.dto.SignUpDto;

public interface MemberService {

	MemberDto signUp(SignUpDto dto);

	MemberDto getMemberById(long memberId);
}
