package com.example.hammertaxi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	

	@GetMapping({"", "/"})// 아무것도 안 붙였을 떄와 붙였을때  index 로이동. 
	public String index() {
		// /WEB-INF/views/index.jsp 	//application.yml 에 spring 경로설정해둠. 
		return "index";
	}
}