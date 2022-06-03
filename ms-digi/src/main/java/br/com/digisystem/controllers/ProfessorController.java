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

import br.com.digisystem.dtos.ProfessorDTO;
import br.com.digisystem.entities.ProfessorEntity;
import br.com.digisystem.services.ProfessorService;

@RestController
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("professores")
	public ResponseEntity<List<ProfessorDTO>> findAll() {
		
		List<ProfessorEntity> profList = this.professorService.findAll();
		List<ProfessorDTO> dtoList = profList.stream().map(ProfessorEntity::toDTO).collect(Collectors.toList());
	
		return ResponseEntity.ok().body(dtoList);
	}
	
	@GetMapping("professores/{id}")
	public ResponseEntity<ProfessorDTO> find(@PathVariable int id) {
		return ResponseEntity.ok().body(this.professorService.find(id).toDTO());
	}
	
	@PostMapping("professores")
	public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorDTO dto) {
		
		return ResponseEntity.ok().body(this.professorService.create(dto.toEntity()).toDTO());
	}
	
	@PatchMapping("professores/{id}")
	public ResponseEntity<ProfessorDTO> update(@PathVariable int id, @RequestBody ProfessorDTO dto) {
		
		return ResponseEntity.ok().body(this.professorService.update(id, dto.toEntity()).toDTO());
	}
	
	@DeleteMapping("professores/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		this.professorService.delete(id);
		
		return ResponseEntity.ok().build();
	}	
}
