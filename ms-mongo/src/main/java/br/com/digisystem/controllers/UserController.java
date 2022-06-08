package br.com.digisystem.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.digisystem.dtos.UserDTO;
import br.com.digisystem.entities.UserEntity;
import br.com.digisystem.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("users")
	public ResponseEntity<List<UserDTO>> getAll() {
		
		List<UserEntity> userList = this.userService.findAll();
		List<UserDTO> dtoList = userList.stream().map(UserEntity::toDTO).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dtoList);
	}
	
	@GetMapping("users/{id}")
	public ResponseEntity<UserDTO> get(@PathVariable String id) {
		
		return ResponseEntity.ok().body(userService.findOne(id).toDTO());
	}
	
	@PostMapping("users")
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) {
		
		UserEntity user = dto.toEntity();
		UserEntity userResponse = this.userService.create(user);
		
		return ResponseEntity.ok().body(userResponse.toDTO());
	}
	
	@PatchMapping("users/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO dto) {
		
		UserEntity user = dto.toEntity();
		UserEntity userResponse = this.userService.update(id, user);
		
		return ResponseEntity.ok().body(userResponse.toDTO());
	}
	
	@DeleteMapping("users/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
	
		this.userService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("users/get-by-name/{name}")
	public ResponseEntity<List<UserDTO>> getByName(@PathVariable String name){
		
		List<UserEntity> userList = this.userService.getByName(name);
		List<UserDTO> userListDTO = userList.stream().map(UserEntity::toDTO).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(userListDTO);
	}
}