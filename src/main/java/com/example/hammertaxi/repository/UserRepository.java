package com.example.hammertaxi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hammertaxi.model.User;


//DAO
//자동으로 bean등록이 된다.
//@Repository // 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {

}
