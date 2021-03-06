package br.com.digisystem.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.digisystem.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	private String email;
	
	@OneToOne
	@JoinColumn(name="address_id")
	private AddressEntity address;
	
	@OneToMany(mappedBy = "user")
	private List<SalesEntity> sales;
	
	public UserDTO toDTO() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, UserDTO.class);
	}
}