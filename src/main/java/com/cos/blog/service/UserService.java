package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	

	@Transactional
	public void 회원가입(User user) {
		user.setRole(RoleType.USER);
		String encPassword = encoder.encode(user.getPassword());
		user.setPassword(encPassword);
		userRepository.save(user);
	}

	@Transactional
	public void 회원수정(User requestUser) {
		//수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		//select를 해서 User 오브젝트를 DB로부터 가져오는 이유가 그 때문
		User persistance = userRepository.findById(requestUser.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
		});
		
		String rawPassword = requestUser.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(requestUser.getEmail());
		//여기서 서비스가 종료되면서 영속성 컨텍스트 변경 감지(더티체킹), update날려줌 -> db로 커밋
		
	}

//	@Transactional(readOnly=true)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
