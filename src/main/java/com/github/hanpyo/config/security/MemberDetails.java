package com.github.hanpyo.config.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.github.hanpyo.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MemberDetails implements UserDetails {

	private Long id;
	private String email;
	private String password;
	private Collection<GrantedAuthority> authorities;

	public MemberDetails(Long id, String email, String password, Collection<GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = Collections.unmodifiableCollection(authorities);
	}

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public static MemberDetails from(Member member) {
		return new MemberDetails(
			member.getId(),
			member.getEmail(),
			member.getPassword(),
			Arrays.asList(new SimpleGrantedAuthority(member.getRole().getRole()))
		);
	}
}
