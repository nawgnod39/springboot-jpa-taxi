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


//	@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);
	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
}