package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		return "/home.html"; //src/main/resources/static
	}
	
	@GetMapping("/temp/img ")
	public String tempImage() {
		return "/a.png";
	}
	
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		return "test";
	}
}
