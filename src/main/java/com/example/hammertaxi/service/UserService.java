package com.example.hammertaxi.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hammertaxi.model.User;
import com.example.hammertaxi.repository.UserRepository;
    
//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다. 메모리를 대신 띄어줌 . 
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	
	
	@Transactional
	public void 회원가입(User user) {
		userRepository.save(user);
	}
	
}



