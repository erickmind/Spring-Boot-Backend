package br.com.digisystem.controllers;

import java.util.ArrayList;
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
	
	@GetMapping("/professor")
	public ResponseEntity<List<ProfessorDTO>> getAll(){
		
		List<ProfessorEntity> prof = new ArrayList<>();
		prof = this.professorService.findAll();
		
		return ResponseEntity.ok().body(prof.stream().map(x -> x.toDTO()).collect(Collectors.toList()));
	}
	
	@GetMapping("/professor/{id}")
	public ResponseEntity<ProfessorDTO> getOne(@PathVariable String id) {
		return ResponseEntity.ok().body(this.professorService.findOne(id).toDTO());
	}
	
	@PostMapping("/professor")
	public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorDTO dto) {
		
		ProfessorEntity prof = dto.toEntity();
		
		return ResponseEntity.ok().body(professorService.create(prof).toDTO());
	}
	
	@PatchMapping("professor/{id}")
	public ResponseEntity<ProfessorDTO> update(@PathVariable String id, @RequestBody ProfessorDTO dto) {
		
		return ResponseEntity.ok().body(this.professorService.update(id, dto.toEntity()).toDTO());
	}
	
	@DeleteMapping("professor/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		this.professorService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
}
