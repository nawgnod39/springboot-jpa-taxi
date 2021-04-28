/*package com.example.hammertaxi.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity//테이블 화  , mysql 에  KAKAOLocation 테이  블이 생성된다. 
public class KakaoLocation {
	   
	
	@Id// 프라이 머리키
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment사용 해당 프로젝트에 연결된  db의 넘버링전략을 따름.
	private int id;//  누가요청했는지~?
	
	@ManyToOne(fetch=FetchType.EAGER)//Many =Many , User = one 한명의 유저는 여러개의 location 사용가능
	@JoinColumn(name ="userId")
	private User user;  //KakaoLocation  에 userkey는 user  table 의 id를 참조한다 . 
	
	
	@Column(nullable = false, length = 100)//location이 0이 되면안되니까 colum 넣어줌
	private String fromLocation;
	
	 
	@Column(nullable = false,  length = 100)
	private String toLocation;
	

	
	@CreationTimestamp
	private LocalDateTime createDate;

}
*/