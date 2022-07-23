package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티 고유의 세션 저장소에 저장을 해준다.
@Getter
public class PrincipalDetail implements UserDetails {
	private User user; //콤포지션 (품고 있는 거)

	
	public PrincipalDetail(User user) {
		this.user = user;
	}
	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	@Override 
	public boolean isAccountNonExpired() { //계정 만료되었는지
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 계정 안 잠겨있는지
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 비밀번호 완료 여부
									
		return true;
	}

	@Override
	public boolean isEnabled() { //계정 활성화 여부
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(() -> {
				return "ROLE_" + user.getRole(); 		
		});
		return collectors;
	}
	
}
