package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	//jpa naming select * from user where username=1?;
	Optional<User> findByUsername(String username);
	
	
}



//JPA Naming 전략
	//User findByUsernameAndPassword(String username, String password);	
//	@Query(value = "SELECT * FROM user WHERE username=?1 AND password=?2", nativeQuery = true)
	//User login(String username, String password);


