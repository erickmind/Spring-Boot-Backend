package br.com.digisystem.entities;

import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.digisystem.dtos.ProfessorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="professor")
public class ProfessorEntity {

	@Id
	private String id;
	private String name;
	private String cpf;
	private String email;
	private String phone;
	
	public ProfessorDTO toDTO() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, ProfessorDTO.class);
	}
}