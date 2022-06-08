package br.com.digisystem.dtos;

import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;

import br.com.digisystem.entities.ProfessorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {

	@Id
	private String id;
	private String name;
	private String cpf;
	private String email;
	private String phone;
	
	public ProfessorEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, ProfessorEntity.class);
	}
}