package com.github.hanpyo.service;

import java.text.MessageFormat;

import com.github.hanpyo.dto.MemberDto;
import com.github.hanpyo.dto.SignUpDto;
import com.github.hanpyo.entity.Member;
import com.github.hanpyo.entity.MemberRole;
import com.github.hanpyo.exception.EntityExistsException;
import com.github.hanpyo.exception.EntityNotFoundException;
import com.github.hanpyo.mapper.MemberMapper;
import com.github.hanpyo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final MemberMapper memberMapper;

	@Override
	@Transactional
	public MemberDto signUp(SignUpDto dto) {
		memberRepository.findByEmail(dto.getEmail()).ifPresent(member -> {
				throw new EntityExistsException(
					MessageFormat.format("member whose email is {0} already exists", member.getEmail())
				);
			}
		);

		memberRepository.findByNickname(dto.getNickname()).ifPresent(member -> {
			throw new EntityExistsException(
				MessageFormat.format("member whose nickname is {0} already exists", member.getNickname())
			);
		});

		String encryptedPassword = passwordEncoder.encode(dto.getPassword());
		Member member = memberMapper.toMember(dto, encryptedPassword, MemberRole.VERIFIED_MEMBER);
		memberRepository.save(member);

		return memberMapper.toMemberDto(member);
	}

	@Override
	@Transactional(readOnly = true)
	public MemberDto getMemberById(long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new EntityNotFoundException(
				MessageFormat.format("member whose id is {0} not found", memberId)
			));
		return memberMapper.toMemberDto(member);
	}
}
