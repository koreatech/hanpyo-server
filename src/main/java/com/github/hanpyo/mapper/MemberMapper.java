package com.github.hanpyo.mapper;

import com.github.hanpyo.dto.MemberDto;
import com.github.hanpyo.dto.SignUpDto;
import com.github.hanpyo.entity.Member;
import com.github.hanpyo.entity.MemberRole;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
	unmappedTargetPolicy = ReportingPolicy.ERROR,
	componentModel = "spring",
	injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface MemberMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "password", source = "encryptedPassword")
	@Mapping(target = "role", source = "role")
	Member toMember(SignUpDto dto, String encryptedPassword, MemberRole role);

	MemberDto toMemberDto(Member member);
}
