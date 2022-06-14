package br.com.digisystem.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.digisystem.entities.UserEntity;
import br.com.digisystem.repositories.impl.CustomRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

@DataMongoTest
@Import(CustomRepositoryImpl.class)
@ExtendWith(SpringExtension.class)
@Slf4j
public class CustomRepositoryTests {
	
	@Autowired
	private CustomRepository customRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MongoTemplate mongoTemp;
	
	@BeforeEach
	void beforeEachTest() {
		mongoTemp.dropCollection(UserEntity.class);
		mongoTemp.createCollection(UserEntity.class);
		//System.out.println("beforeEach");
		log.info("beforeEach");
	}
	
	
}