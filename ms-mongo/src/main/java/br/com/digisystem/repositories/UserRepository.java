package br.com.digisystem.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.digisystem.entities.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String>{

}