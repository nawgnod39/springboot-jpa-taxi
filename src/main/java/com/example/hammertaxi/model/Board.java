package com.example.hammertaxi.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment사용
	private int id;

	@Column(nullable = false, length = 100) // 제목이 null이되면 안되기때문에 column 사용
	private String title;

	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨. 우리가적는 일반적인글이 디자인 된다는말. 글자용량이 커짐 .


	private int count; // 조회수

	@ManyToOne(fetch = FetchType.EAGER) // Many = Many, User = One 한명의 유저는 여러개의 게시글을 쓸수있다.
	@JoinColumn(name = "userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 연관관계의 주인이 아님 DB에 칼럼 만들지 않음
	private List<Reply> reply;// 하나의 게시글은 여러게 댓글가능 onetomany
//reply 테이블에있는 board 아이디를 넣어주고  
	@CreationTimestamp
	private LocalDateTime createDate;
	
	
}