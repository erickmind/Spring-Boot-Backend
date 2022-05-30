package br.com.digisystem.dtos;

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
	private String nome;
	private String email;
	
	public UsuarioEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, UsuarioEntity.class);
	}
}

