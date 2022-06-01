package br.com.digisystem.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.digisystem.dtos.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="products")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double value;
	private String name;
	private String description;
	
	@ManyToMany
	@JoinTable(name="product_sales",
				joinColumns = @JoinColumn(name="product_id"),
				inverseJoinColumns = @JoinColumn(name="sales_id")
	)
	private List<SalesEntity> sales;
	
	public ProductDTO toDTO() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, ProductDTO.class);
	}
}
