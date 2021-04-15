package com.example.hammertaxi.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hammertaxi.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}