package br.com.digisystem.dtos;

import org.modelmapper.ModelMapper;

import br.com.digisystem.entities.SalesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesDTO {

	private int id;
	private double totalValue;
	
	public SalesEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, SalesEntity.class);
	}	
}