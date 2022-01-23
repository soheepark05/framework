package com.kh.mvc.member.controller;
import javax.servlet.http.HttpServletRequest;

//수정해야댐
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	@RequestMapping(value="/login", method = {RequestMethod.POST})
	public String login() {
		
		
		log.info("login() - 호출");
		
		return "home";
	}

// Http어쩌구저쩌구 수정
@PostMapping("/login")
public String login(HttpServletRequest request) {
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	log.info("login-호출 : {} {}",id,password);
	
	return "home";
	
}

}