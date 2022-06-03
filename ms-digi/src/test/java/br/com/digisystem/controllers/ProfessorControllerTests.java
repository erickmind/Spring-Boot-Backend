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

import br.com.digisystem.dtos.ProfessorDTO;
import br.com.digisystem.entities.ProfessorEntity;
import br.com.digisystem.repositories.ProfessorRepository;
import br.com.digisystem.services.ProfessorService;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class ProfessorControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private ProfessorRepository profRepository;
	
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
	
	@Test
	void createTest() throws Exception{
		
		ProfessorDTO prof = new ProfessorDTO();
		prof.setNome("Joao");
		prof.setEmail("joao.prof@gmail.com");
		prof.setCpf("21321355353");
		prof.setTelefone("3213212421");
		
		ResultActions response = mockMvc.perform(
				post("/professores")
				.contentType("application/json")
				.content(mapper.writeValueAsString(prof)));
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		ProfessorDTO savedProf = mapper.readValue(resultStr, ProfessorDTO.class);
		
		assertThat(savedProf.getId()).isPositive();
		assertThat(savedProf.getNome()).isEqualTo(prof.getNome());
		assertThat(savedProf.getEmail()).isEqualTo(prof.getEmail());
		assertThat(savedProf.getCpf()).isEqualTo(prof.getCpf());
		assertThat(savedProf.getTelefone()).isEqualTo(prof.getTelefone());
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	void updateTest() throws Exception{
		
		int id = 1;
		
		ProfessorDTO prof = new ProfessorDTO();
		prof.setNome("Joao");
		prof.setEmail("joao.prof@gmail.com");
		prof.setCpf("21321355353");
		prof.setTelefone("3213212421");
		
		ResultActions response = mockMvc.perform(
				patch("/professores/"+ id)
				.contentType("application/json")
				.content(mapper.writeValueAsString(prof)));
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		ProfessorDTO modifiedProf = mapper.readValue(resultStr, ProfessorDTO.class);
		
		assertThat(modifiedProf.getNome()).isEqualTo(prof.getNome());
		assertThat(modifiedProf.getEmail()).isEqualTo(prof.getEmail());
		assertThat(modifiedProf.getCpf()).isEqualTo(prof.getCpf());
		assertThat(modifiedProf.getTelefone()).isEqualTo(prof.getTelefone());
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	void deleteTest() throws Exception{
		
		ProfessorEntity dto = new ProfessorEntity();
		dto.setNome("test");
		dto.setEmail("test@test.com");
		dto.setCpf("123245613");
		dto.setTelefone("13243656");
		
		ProfessorEntity prof = profRepository.save(dto);
		
		ResultActions response = mockMvc.perform(
				delete("/professores/"+ prof.getId())
				.contentType("application/json"));
		
		MvcResult result = response.andReturn();
		
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}
}
