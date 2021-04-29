package com.example.hammertaxi.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.hammertaxi.config.auth.PrincipalDetail;
//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
//그냥 주소가 / 이면 index.jsp 허용
//static이하에 있는 /js/**, /css/**, /image/**


@Controller
public class UserController {

	@GetMapping("/auth/joinForm")//인증을 위해 auth 사용 
	public String joinForm() {	
		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {	
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {	
	  return "user/updateForm";
	}
}