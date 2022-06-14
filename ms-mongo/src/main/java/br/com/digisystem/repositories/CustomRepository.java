package br.com.digisystem.repositories;

import br.com.digisystem.entities.UserEntity;

public interface CustomRepository {
	
	UserEntity updateUser(String id, String name);
}
