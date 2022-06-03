package br.com.digisystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.digisystem.entities.UserEntity;
import br.com.digisystem.utils.userUtil;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTests extends userUtil{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	void findAllTests() throws Exception {
		
		UserEntity user = new UserEntity();
		user.setEmail("test@test.com");
		user.setName("test");
		
		testEntityManager.persist(user);
		
		List<UserEntity> list = userRepository.findAll();
		
		assertThat( list.size()).isEqualTo(1);
	}
	
	@Test
	void findByIdWhenIsFound() {
		
		UserEntity newUser = createValidUser();
		
		testEntityManager.persist(newUser);
		
		Optional<UserEntity> user = userRepository.findById(newUser.getId());
		
		assertThat(user).isPresent();
	}
	
	@Test
	void findByIdWhenIsNotFound() {
		
		Optional<UserEntity> user = userRepository.findById(1);

		assertThat(user).isEmpty();
		
	}
	
	@Test
	void saveTest() {
		
		UserEntity user = createValidUser();
		
		UserEntity savedUser = this.userRepository.save(user);
		
		assertThat(savedUser.getId()).isPositive();
		assertThat(savedUser.getName()).isEqualTo(user.getName());
		assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	void deleteTest() {
		
		UserEntity user = createValidUser();
		
		testEntityManager.persist(user);
		
		userRepository.deleteById(user.getId());
		
		Optional<UserEntity> deleted = userRepository.findById(user.getId());
		
		assertThat(deleted).isEmpty();
	}
}
