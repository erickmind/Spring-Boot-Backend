package br.com.digisystem.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.digisystem.entities.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String>{

	@Query(" { name: /?0/ } ")
	public List<UserEntity> searchByNameNative(String name);
	
	@Query(" { name: /:#{#name}/ } ")
	public List<UserEntity> searchByName(@Param("name") String name);
}