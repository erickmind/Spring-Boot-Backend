package br.com.digisystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.digisystem.dtos.SalesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sales")
public class SalesEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="total_value")
	private double totalValue;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	public SalesDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, SalesDTO.class);
	}
}