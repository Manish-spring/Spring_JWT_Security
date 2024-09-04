package com.Coding.SecurityApplication.SecurityApplication;

import com.Coding.SecurityApplication.SecurityApplication.entities.User;
import com.Coding.SecurityApplication.SecurityApplication.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
		User user = new User();
		user.setId(2L);
		user.setEmail("manish.kumar@gmail.com");
		user.setPassword("Tewst123");

		String tokenValue = jwtService.generateAccessToken(user);

		System.out.println("Token value ---" + tokenValue);

		Long userId = jwtService.generateIdFromToken(tokenValue);

		System.out.println("user Id - " + userId);

	}

}
