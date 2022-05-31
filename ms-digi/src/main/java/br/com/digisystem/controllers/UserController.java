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
	private UserService usuarioService;
	
	@GetMapping("usuarios")
	public ResponseEntity<List<UserDTO>> getAll() {
		
		List<UserEntity> usuarioList = this.usuarioService.findAll();
		List<UserDTO> dtoList = usuarioList.stream().map(x -> x.toDTO()).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dtoList);
	}
	
	@GetMapping("usuarios/{id}")
	public ResponseEntity<UserDTO> get(@PathVariable int id) {
		
		return ResponseEntity.ok().body(usuarioService.findOne(id).toDTO());
	}
	
	@PostMapping("usuarios")
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) {
		
		UserEntity usuario = dto.toEntity();
		UserEntity usuarioResponse = this.usuarioService.create(usuario);
		
		return ResponseEntity.ok().body(usuarioResponse.toDTO());
	}
	
	@PatchMapping("usuarios/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable int id, @RequestBody UserDTO dto) {
		
		UserEntity usuario = dto.toEntity();
		UserEntity usuarioResponse = this.usuarioService.update(id, usuario);
		
		return ResponseEntity.ok().body(usuarioResponse.toDTO());
	}
	
	@DeleteMapping("usuarios/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
	
		this.usuarioService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("usuarios/get-by-nome/{nome}")
	public ResponseEntity<List<UserDTO>> getByNome(@PathVariable String nome){
		
		List<UserEntity> usuarioList = this.usuarioService.getByNome(nome);
		List<UserDTO> usuarioListDTO = usuarioList.stream().map(x -> x.toDTO()).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(usuarioListDTO);
	}
}
