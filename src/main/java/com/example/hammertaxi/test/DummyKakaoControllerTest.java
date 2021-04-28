/*
 * package com.example.hammertaxi.test;
 * 
 * import javax.persistence.Column; import javax.persistence.JoinColumn; import
 * javax.persistence.ManyToOne;
 * 
 * import org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.example.hammertaxi.model.User;
 * 
 * @Controller//
 * 
 * public class DummyKakaoControllerTest {
 * 
 * @PostMapping("/dummy/join") public String kLocation(String userkey,String
 * fromLocation,String toLocation) { return "위치값저장됐습니다."; } }
 */

/*
 * @ManyToOne//Many =Many , User = one
 * 
 * @JoinColumn(name ="userkey")
 *  private User user; //KakaoLocation 에 userkey는
 * user table 의 id를 참조한다 .
 * 
 * 
 * @Column(nullable = false, length = 100)//location이 0이 되면안되니까 colum 넣어줌
 * private String fromLocation;
 * 
 * 
 * @Column(nullable = false, length = 100) private String toLocation;
 */