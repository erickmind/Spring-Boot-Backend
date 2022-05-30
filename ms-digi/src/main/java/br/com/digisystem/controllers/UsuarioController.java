package br.com.digisystem.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.digisystem.dtos.UsuarioDTO;
import br.com.digisystem.entities.UsuarioEntity;
import br.com.digisystem.services.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("usuarios")
	public ResponseEntity<List<UsuarioDTO>> getAll() {
		
		List<UsuarioEntity> usuarioList = this.usuarioService.findAll();
		List<UsuarioDTO> dtoList = usuarioList.stream().map(x -> x.toDTO()).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dtoList);
	}
	
	@GetMapping("usuarios/{id}")
	public ResponseEntity<UsuarioDTO> get(@PathVariable int id) {
		
		return ResponseEntity.ok().body(usuarioService.find(id).toDTO());
	}
	
	@PostMapping("usuarios")
	public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO dto) {
		
		UsuarioEntity usuario = dto.toEntity();
		UsuarioEntity usuarioResponse = this.usuarioService.create(usuario);
		
		return ResponseEntity.ok().body(usuarioResponse.toDTO());
	}
	
	@PatchMapping("usuarios/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable int id, @RequestBody UsuarioDTO dto) {
		
		UsuarioEntity usuario = dto.toEntity();
		UsuarioEntity usuarioResponse = this.usuarioService.update(id, usuario);
		
		return ResponseEntity.ok().body(usuarioResponse.toDTO());
	}
	
	@DeleteMapping("usuarios/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
	
		this.usuarioService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("usuarios/get-by-nome/{nome}")
	public ResponseEntity<List<UsuarioDTO>> getByNome(@PathVariable String nome){
		
		List<UsuarioEntity> usuarioList = this.usuarioService.getByNome(nome);
		List<UsuarioDTO> usuarioListDTO = usuarioList.stream().map(x -> x.toDTO()).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(usuarioListDTO);
	}
}
