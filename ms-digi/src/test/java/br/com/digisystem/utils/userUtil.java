package br.com.digisystem.utils;

import br.com.digisystem.entities.UserEntity;

public class userUtil {

	protected UserEntity createValidUser() {
		
		UserEntity user = new UserEntity();
		
		user.setEmail("email@email.com");
		user.setName("Test name");
		
		return user;
	}
}
