package com.example.hammertaxi.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.hammertaxi.dto.ResponseDto;
import com.example.hammertaxi.model.RoleType;
import com.example.hammertaxi.model.User;
import com.example.hammertaxi.service.UserService;



@RestController
public class UserApiController {

	@Autowired
	private UserService userService;


	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email 
		System.out.println("UserApiController : save 호출됨");

		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
	}
	
}