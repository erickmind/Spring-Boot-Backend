package br.com.digisystem.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.digisystem.entities.UserEntity;
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
		user.setNome("Test");
		user.setEmail("test@digisystem.com.br");
		
		simulationList.add(user);
		
		//Return the created list as a Mock without really calling the Repository Layer
		when( userRepository.findAll() ).thenReturn(simulationList);
		
		List<UserEntity> list = userService.findAll();
		
		assertThat(list.get(0).getNome()).isEqualTo(simulationList.get(0).getNome());
		assertThat(list.get(0).getEmail()).isEqualTo(simulationList.get(0).getEmail());
	}	
}