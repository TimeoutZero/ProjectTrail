package io.redspark.ireadme.test.builders;

import io.redspark.ireadme.entity.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import aleph.AbstractBuilder;

public class UserBuilder extends AbstractBuilder<User>{

	public static UserBuilder user() {
		return new UserBuilder()
			.email("lucas.gmmartins@gmail.com")
			.password("12345");

	}
	public static UserBuilder user(String email, String password) {
		return new UserBuilder()
		.email(email)
		.password(password);
	}
	
	public UserBuilder email(String email) {
		return set("email", email);
	}
	
	public UserBuilder password(String password) {
		return set("password", new BCryptPasswordEncoder().encode(password));
	}
}
