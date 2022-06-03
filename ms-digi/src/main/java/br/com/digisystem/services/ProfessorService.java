package br.com.digisystem.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digisystem.entities.ProfessorEntity;
import br.com.digisystem.exceptions.ObjNotFoundException;
import br.com.digisystem.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	Logger logger = Logger.getLogger(ProfessorService.class.getName());
	
	public List<ProfessorEntity> findAll() {
		
		return this.professorRepository.findAll();
	}
	
	public ProfessorEntity find(int id) {
		return this.professorRepository.findById(id).orElseThrow(() -> new ObjNotFoundException("Professor of id "+ id + "not found"));
	}
	
	public ProfessorEntity create(ProfessorEntity prof) {
		return this.professorRepository.save(prof);
	}
	
	public ProfessorEntity update(int id, ProfessorEntity prof) {
		ProfessorEntity profUpdate = this.professorRepository.findById(id).orElseThrow(() -> new ObjNotFoundException("Professor of id "+ id + "not found"));
				
		profUpdate.setNome(prof.getNome());
		profUpdate.setCpf(prof.getCpf());
		profUpdate.setEmail(prof.getEmail());
		profUpdate.setTelefone(prof.getTelefone());	
			
		return this.professorRepository.save(profUpdate);
	}
	
	public void delete(int id) {
		try {
			this.professorRepository.deleteById(id);
		}catch (Exception e) {
			logger.log(Level.WARNING,"Erro ao deletar registro");
		}
	}
}
