package br.com.digisystem.dtos;

import org.modelmapper.ModelMapper;

import br.com.digisystem.entities.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	
	private int id;
	
	private String Street;
	private String number;
	private String cep;
	private String complement;
	private String district;
	private String fu;
	
	public AddressEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, AddressEntity.class);
	}
}
