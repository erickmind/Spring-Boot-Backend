package br.com.digisystem.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.digisystem.entities.UserEntity;
import br.com.digisystem.exceptions.ObjNotFoundException;
import br.com.digisystem.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTests {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	void findAllTest() throws Exception{
		
		List<UserEntity> simulationList = new ArrayList<>();
		UserEntity user = new UserEntity();
		user.setName("Test");
		user.setEmail("test@digisystem.com.br");
		
		simulationList.add(user);
		
		//Return the created list as a Mock without really calling the Repository Layer
		when( userRepository.findAll() ).thenReturn(simulationList);
		
		List<UserEntity> list = userService.findAll();
		
		assertThat(list.get(0).getName()).isEqualTo(simulationList.get(0).getName());
		assertThat(list.get(0).getEmail()).isEqualTo(simulationList.get(0).getEmail());
	}	
	
	@Test
	void findOneTestWhenIsFound() throws Exception{
		
		int id = 2;
		
		UserEntity user = new UserEntity();
		user.setName("Test");
		user.setEmail("test@digisystem.com.br");
		user.setId(id);
		
		Optional<UserEntity> optional = Optional.of(user);
		
		when( this.userRepository.findById(id)).thenReturn(optional);
		
		UserEntity returnedUser = userService.findOne(id);
		
		assertThat(returnedUser.getName()).isEqualTo(returnedUser.getName());
		assertThat(returnedUser.getEmail()).isEqualTo(returnedUser.getEmail());
	}
	
	@Test
	void findOneWhenNotFound() throws Exception{
		
		int id = 2;
		
		when( this.userRepository.findById(id)).thenThrow( new ObjNotFoundException("Erro"));
		
		assertThrows(ObjNotFoundException.class, () -> userService.findOne(id));
	}
	
	@Test
	void createTest() throws Exception{
		
		int id = 1;
		
		UserEntity user = new UserEntity();
		user.setName("Bla");
		user.setEmail("bla@gmail.com");
		
		UserEntity userReturn = new UserEntity();
		userReturn.setName("Bla");
		userReturn.setEmail("bla@gmail.com");
		userReturn.setId(id);
		
		when(userRepository.save(user)).thenReturn(userReturn);
		
		UserEntity savedUser = userService.create(user);
		
		assertThat(savedUser.getName()).isEqualTo(user.getName());
		assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
		assertThat(savedUser.getId()).isPositive();
	}
	
//	@Test
//	void updateTestWhenFound() throws Exception{
//		
//		int id = 2;
//		
//		
//	}
	
//	@Test
//	void updateTestWhenNotFound() throws Exception{
//		
//		int id = 2;
//		
//		
//	}
}