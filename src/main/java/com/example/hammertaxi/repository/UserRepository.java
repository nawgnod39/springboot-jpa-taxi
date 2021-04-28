package com.example.hammertaxi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.hammertaxi.model.User;
// DAO
// 자동으로 bean등록이 된다.
// @Repository // 생략 가능하다.


public interface UserRepository extends JpaRepository<User, Integer>{

	//USER findByUsernameAndPassword(String username, String password) 이렇게 쓰면 
	//SELECT * FROM user WHERE username = ? AND password = ?   ? 에 username과 password 가 들어온다.  이게 jpa naming 전략
	//------------------------------------------------------------------------------------------------------
	//@Query(value="SELECT * FROM user WHERE username = ? AND password = ?",nativeQuery=true)
	//User login (String username ,String password); 이렇게 두줄 적어주면 UserRepository.login 을하면  위에커리문을 해주고 retrun을 User 객체로 해줌 .
	
	Optional<User> findByUsername(String username);
}