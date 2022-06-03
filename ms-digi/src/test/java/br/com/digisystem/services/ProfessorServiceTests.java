package br.com.digisystem.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import br.com.digisystem.entities.ProfessorEntity;
import br.com.digisystem.exceptions.ObjNotFoundException;
import br.com.digisystem.repositories.ProfessorRepository;
import br.com.digisystem.utils.ProfessorUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProfessorServiceTests extends ProfessorUtil{

	@Autowired
	private ProfessorService profService;
	
	@MockBean
	private ProfessorRepository profRepository;
	
	@Test
	void findAllTest() {
		
		List<ProfessorEntity> simulationList = new ArrayList<>();
		
		ProfessorEntity prof = new ProfessorEntity();
		prof.setCpf("142121321421");
		prof.setEmail("test@test.com");
		prof.setNome("test");
		prof.setTelefone("432434643");
		
		simulationList.add(prof);
		
		when(profRepository.findAll()).thenReturn(simulationList);
		
		List<ProfessorEntity> list = profService.findAll();
		
		assertThat(list.get(0).getNome()).isEqualTo(simulationList.get(0).getNome());
		assertThat(list.get(0).getEmail()).isEqualTo(simulationList.get(0).getEmail());
		assertThat(list.get(0).getCpf()).isEqualTo(simulationList.get(0).getCpf());
		assertThat(list.get(0).getTelefone()).isEqualTo(simulationList.get(0).getTelefone());		
	}
	
	@Test
	void findOneTestWhenFound() {
		
		int id = 2;
		
		ProfessorEntity prof = new ProfessorEntity();
		prof.setCpf("142121321421");
		prof.setEmail("test@test.com");
		prof.setNome("test");
		prof.setTelefone("432434643");
		prof.setId(id);
		
		Optional<ProfessorEntity> optional = Optional.of(prof);
		
		when(profRepository.findById(id)).thenReturn(optional);
		
		ProfessorEntity profResponse = profService.find(id);
		
		assertThat(profResponse.getNome()).isEqualTo(prof.getNome());
		assertThat(profResponse.getEmail()).isEqualTo(prof.getEmail());
		assertThat(profResponse.getCpf()).isEqualTo(prof.getCpf());
		assertThat(profResponse.getTelefone()).isEqualTo(prof.getTelefone());
	}
	
	@Test
	void findOneTestWhenNotFound() {
		
		when(profRepository.findById(1)).thenThrow(new ObjNotFoundException("Erro"));
		
		assertThrows(ObjNotFoundException.class, () -> profRepository.findById(1));
	}
	
	@Test
	void createTest() {
		
		int id = 2;
		
		ProfessorEntity prof = new ProfessorEntity();
		prof.setCpf("142121321421");
		prof.setEmail("test@test.com");
		prof.setNome("test");
		prof.setTelefone("432434643");
		
		ProfessorEntity profResponse = new ProfessorEntity();
		profResponse.setCpf("142121321421");
		profResponse.setEmail("test@test.com");
		profResponse.setNome("test");
		profResponse.setTelefone("432434643");
		profResponse.setId(id);
		
		when(profRepository.save(prof)).thenReturn(profResponse);
		
		ProfessorEntity savedProf = profRepository.save(prof);
		
		assertThat(savedProf.getNome()).isEqualTo(prof.getNome());
		assertThat(savedProf.getEmail()).isEqualTo(prof.getEmail());
		assertThat(savedProf.getCpf()).isEqualTo(prof.getCpf());
		assertThat(savedProf.getTelefone()).isEqualTo(prof.getTelefone());
		assertThat(savedProf.getId()).isPositive();
	}
	
	@Test
	void updateTestWhenFound() {
		
		int id = 2;
		
		ProfessorEntity prof = createValidProf();
		
		Optional<ProfessorEntity> optional = Optional.of(prof);
		
		when(profRepository.findById(id)).thenReturn(optional);
		when(profRepository.save(prof)).thenReturn(prof);
		
		ProfessorEntity updatedProf = profService.update(id, prof);
		
		assertThat(updatedProf.getNome()).isEqualTo(prof.getNome());
		assertThat(updatedProf.getEmail()).isEqualTo(prof.getEmail());
		assertThat(updatedProf.getCpf()).isEqualTo(prof.getCpf());
		assertThat(updatedProf.getTelefone()).isEqualTo(prof.getTelefone());
	}
	
	@Test
	void updateTestWhenNotFound() {
		
		when(profRepository.findById(1)).thenThrow(new ObjNotFoundException("Erro"));
		
		assertThrows(ObjNotFoundException.class, () -> profService.update(1, null));
	}
	
	@Test
	void deleteTest() {
		
		assertDoesNotThrow(() -> profService.delete(1));
		
		verify(profRepository, times(1)).deleteById(1);	
	}
}
