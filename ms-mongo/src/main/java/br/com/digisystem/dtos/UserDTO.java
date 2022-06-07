package br.com.digisystem.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.digisystem.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private String id;
	
	@NotEmpty(message="The field name is Required")
	@NotBlank(message="The field name cannot be empty")
	@Length(min=3, message="The field name must have at least 3 characters")
	private String name;
	private String email;
	
	public UserEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, UserEntity.class);
	}
}

