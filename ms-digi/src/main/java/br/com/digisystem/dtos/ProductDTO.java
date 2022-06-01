package br.com.digisystem.dtos;

import org.modelmapper.ModelMapper;

import br.com.digisystem.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private int id;
	private double value;
	private String name;
	private String description;
	
	public ProductEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, ProductEntity.class);
	}
}