package br.com.digisystem.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digisystem.dtos.AddressDTO;
import br.com.digisystem.entities.AddressEntity;
import br.com.digisystem.services.AddressService;

@RestController
public class AddressController {
	
	@Autowired	
	private AddressService addressService;
	
	@GetMapping("address")
	public ResponseEntity<List<AddressDTO>> getAll(){
		
		List<AddressEntity> list = this.addressService.getAll();
		List<AddressDTO> listDTO = list.stream().map(AddressEntity::toDTO).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
}
