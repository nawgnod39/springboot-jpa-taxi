package com.example.hammertaxi.test;


import java.util.List;
import java.util.function.Supplier;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.hammertaxi.model.RoleType;
import com.example.hammertaxi.model.User;
import com.example.hammertaxi.repository.UserRepository;

//html파일이 아니라 data를 리턴해주는 controller = > 이게 RestController
@RestController// 응답만 해줄수있도록. 
public class DummyControllerTest {

	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
		// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
		// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다.
		// email, password 를 받아야함 .수정할것이기 떄문에.

	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {//귀찮으면 excetion 을 걸어도됨.
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}

		return "삭제되었습니다. id : "+id;
	}

	@Transactional // 함수 종료시에 자동 commit 이 됨.
		@PutMapping("/dummy/user/{id}")// 업데이트는 putmapping 사용 
		public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //json 데이터를 요청 => Java Object(MessageConverter의 Jackson라이브러리가 변환해서 받아줘요.)
			System.out.println("id : "+id);
			System.out.println("password : "+requestUser.getPassword());
			System.out.println("email : "+requestUser.getEmail());

			User user = userRepository.findById(id).orElseThrow(()->{
				return new IllegalArgumentException("수정에 실패하였습니다.");
			});
			user.setPassword(requestUser.getPassword());
			user.setEmail(requestUser.getEmail());
			//user.setEmail(requestUser.getEmail());

			// userRepository.save(user);
			// 더티 체킹
			return user;
		}

	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll(); 
	}

	// 한페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
	//DESC 최근에 가입한 순서.
		Page<User> pagingUser = userRepository.findAll(pageable);

		List<User> users = pagingUser.getContent();
		return pagingUser;
	}

	// {id} 주소로 파마레터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/5
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		// 그럼 return null 이 리턴이 되자나.. 그럼 프로그램에 문제가 있지 않겠니? 그래서  
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!!

//     // 람다식  바로 return 으로 설정신경안쓰고 람다식을 이용하여 작성할 수 도있다.
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});

		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {//해당 유저를 찾고 , orelsethrow를 걸어줌 .못찾을수 있으니까 .
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 사용자가 없습니다.");
			}
		});
		//user 객체를 return 해주는 데 웹브라우저는 html 만 이해할수있다. 자바가 들고있는 object 를 이해할 수 없음.
		// 요청 : 웹브라우저   
		// user 객체 = 자바 오브젝트 그래서 
		// 변환 ( 웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user;
	}


	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")//회원가입 
	public String join(User user) { // key=value (약속된 규칙)
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());

		user.setRole(RoleType.USER);
		userRepository.save(user); // save 해서 user 넣어 주면 회원 가입이된다.
		return "회원가입이 완료되었습니다.";
	}
}
