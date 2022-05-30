package br.com.digisystem.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.digisystem.entities.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	
	private int id;
	
	@NotEmpty(message="The field name is Required")
	@NotBlank(message="The field name cannot be empty")
	@Length(min=3, message="The field name should have at least 3 characters")
	private String nome;
	private String email;
	
	public UsuarioEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, UsuarioEntity.class);
	}
}

