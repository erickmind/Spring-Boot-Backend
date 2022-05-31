package br.com.digisystem.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

import br.com.digisystem.dtos.ProfessorDTO;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ProfessorControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void getAllTest() throws Exception{
		
		ResultActions response = mockMvc.perform(
				get("/professores")
				.contentType("application/json"));
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		
		ProfessorDTO[] list = mapper.readValue(resultStr, ProfessorDTO[].class);
		
		assertThat(list).isNotEmpty();
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	void getOneTest() throws Exception{
		
		int id = 1;
		
		ResultActions response = mockMvc.perform(
				get("/professores/" + id)
				.contentType("application/json"));
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		
		ProfessorDTO prof = mapper.readValue(resultStr, ProfessorDTO.class);
		
		assertThat(prof.getId()).isEqualTo(id);
	}
}
