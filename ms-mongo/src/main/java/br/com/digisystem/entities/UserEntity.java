package br.com.digisystem.entities;

import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.digisystem.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity {
	
	@Id
	private String id;
	private String name;
	private String email;
	
	public UserDTO toDTO() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, UserDTO.class);
	}
}