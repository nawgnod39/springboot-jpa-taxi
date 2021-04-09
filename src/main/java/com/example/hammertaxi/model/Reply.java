package com.example.hammertaxi.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment

	@Column(nullable = false, length = 200)
	private String content;

	@ManyToOne
	@JoinColumn(name="boardId")
	private Board board;

	@ManyToOne
	@JoinColumn(name="userId")//어디랑연관된지 모르니까  하나의 게시글엔 여러개 가필요 , manytoone 사용  onetoone 이면 하나의 글에 하나의답면만달림. 
	private User user;

	@CreationTimestamp
	private LocalDateTime createDate;
}