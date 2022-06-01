package br.com.digisystem.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.digisystem.dtos.UserDTO;
import br.com.digisystem.entities.UserEntity;
import br.com.digisystem.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void findAllTest() throws Exception{
		
		ResultActions response = mockMvc.perform(
				get("/users")
				.contentType("application/json"));
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		
		UserDTO[] list = mapper.readValue(resultStr, UserDTO[].class);
		
		assertThat(list).isNotEmpty();
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	void findOneTest() throws Exception{
		
		int id = 1;
		
		ResultActions response = mockMvc.perform(
				get("/users/" + id)
				.content("application/json"));
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		
		UserDTO user = mapper.readValue(resultStr, UserDTO.class);
		
		assertThat(user.getId()).isEqualTo(id);		
	}
	
	@Test
	void createTest() throws Exception{
		
		UserDTO user = new UserDTO();
		
		user.setName("Joao");
		user.setEmail("jaozinho@digisystem.com");
		
		ResultActions response = mockMvc.perform(
				post("/users")
				.contentType("application/json")
				.content(mapper.writeValueAsString(user)));
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		UserDTO userSaved = mapper.readValue(resultStr, UserDTO.class);
		
		assertThat(userSaved.getId()).isPositive();
		assertThat(userSaved.getName()).isEqualTo(user.getName());
		assertThat(userSaved.getEmail()).isEqualTo(user.getEmail());
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	void updateTest() throws Exception{
		
		int id = 1;
		
		UserDTO user = new UserDTO();
		
		user.setName("JUnit Test");
		user.setEmail("junit@digisystem.com.br");
		
		ResultActions response = mockMvc.perform(
				patch("/users/"+ id)
				.contentType("application/json")
				.content(mapper.writeValueAsString(user)));
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		
		UserDTO modifiedUser = mapper.readValue(resultStr, UserDTO.class);
		
		assertThat(modifiedUser.getId()).isEqualTo(id);
		assertThat(modifiedUser.getName()).isEqualTo(user.getName());
		assertThat(modifiedUser.getEmail()).isEqualTo(user.getEmail());
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	void deleteTest() throws Exception{
		
		UserEntity user = new UserEntity();
		
		user.setName("mock");
		user.setEmail("mock@digisystem.com");
		
		UserEntity userSaved = this.userRepository.save(user);
		
		ResultActions response = mockMvc.perform(
				delete("/users/"+ userSaved.getId())
				.contentType("application/json"));
		
		MvcResult result = response.andReturn();
		
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void getByNomeTest() throws Exception{
		
		String nome = "Erick";
		
		ResultActions response = mockMvc.perform(
				get("/users/get-by-name/" + nome)
				.contentType("application/json"));
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		
		UserDTO[] list = mapper.readValue(resultStr, UserDTO[].class);
		
		assertThat(list).isNotEmpty();
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}
}
