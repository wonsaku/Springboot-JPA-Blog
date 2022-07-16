package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

	private static final String TAG="HttpController Test: ";
	//http://localhost:8080/http/get (select)
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG + "getter: " + m.getUsername());
		m.setUsername("ssar2");
		System.out.println(TAG + "setter: " + m.getUsername());	
		return "lombok test완료";
		
	}
	
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
	
		return "get요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " +m.getEmail() ;
	}
	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " +m.getEmail() ;
	}
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " +m.getEmail() ;
	}
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
