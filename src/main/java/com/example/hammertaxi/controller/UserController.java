package com.example.hammertaxi.controller;



import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import com.example.hammertaxi.config.auth.PrincipalDetail;
import com.example.hammertaxi.model.KakaoProfile;
import com.example.hammertaxi.model.OAuthToken;
import com.example.hammertaxi.model.User;
import com.example.hammertaxi.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
//그냥 주소가 / 이면 index.jsp 허용
//static이하에 있는 /js/**, /css/**, /image/**

@Controller
public class UserController {
	
	@Value("${takdong.key}")
	private String takdongKey;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;

	@GetMapping("/auth/joinForm") // 인증을 위해 auth 사용
	public String joinForm() {
		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

	@GetMapping("/auth/kakao/callback")
	public  String kakaoCallback(String code) {
		//data를 return 해주는 함수 
		
		RestTemplate rt = new RestTemplate();
		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "697b6a57b2ac6bd88064c58c9d5cfc16");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
	    HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
	    		new HttpEntity<>(params, headers);
		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);
		// Gson, Json Simple, ObjectMapper
				ObjectMapper objectMapper = new ObjectMapper();
				OAuthToken oauthToken=null;
				try {
					 oauthToken = objectMapper.readValue(response.getBody(),OAuthToken.class);
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("카카오 엑세스 토큰 :"+oauthToken.getAccess_token());
				
				RestTemplate rt2 = new RestTemplate();

				// HttpHeader 오브젝트 생성
				HttpHeaders headers2 = new HttpHeaders();
				headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
				headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

				// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
				HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
						new HttpEntity<>(headers2);

				// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
				ResponseEntity<String> response2 = rt2.exchange(
						"https://kapi.kakao.com/v2/user/me",
						HttpMethod.POST,
						kakaoProfileRequest2,
						String.class
				);
				System.out.println(response2.getBody());
				
				ObjectMapper objectMapper2 = new ObjectMapper();
				KakaoProfile kakaoProfile = null;
				try {
					kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				System.out.println("카카오 아이디 "+kakaoProfile.getId());
				System.out.println("카카오 이메일"+kakaoProfile.getKakao_account().getEmail());
				System.out.println("카카오 유저네임 "+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
				System.out.println("hammer 서버 이메일 "+kakaoProfile.getKakao_account().getEmail());
				
			//	UUID garbagePassword =UUID.randomUUID(); 중복되지않는 특정값을 만들어 내는 알고리즘 . 로그인할때마다 바뀌어있음.
				System.out.println("블로그패스워드 "+takdongKey);
				 
				User kakaoUser =User.builder()//User 오브젝트를 넣어야함
						.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
						.password(takdongKey)
						.email(kakaoProfile.getKakao_account().getEmail())
						.build();
				
				//65 ,4658
				
				
				
				//가입자인지 비가입자인지 체크해서 처리해야함
				User originUser =userService.회원찾기(kakaoUser.getUsername());
				if(originUser.getUsername() ==null) {
					System.out.println("기존회원이 아닙니다. 회원가입이 진행됩니다.");
					userService.회원가입(kakaoUser);
				}
				//로그인처리
				Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),takdongKey));
				SecurityContextHolder.getContext().setAuthentication(authentication);

				
				return "redirect:/";
	}
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
}