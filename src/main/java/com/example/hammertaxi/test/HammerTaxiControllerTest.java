package com.example.hammertaxi.test;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HammerTaxiControllerTest {
	
	@GetMapping("/test/hello")
	public String hello() {
	
		return "<h1>hello spring hoot </h1>";
	
	}
}
