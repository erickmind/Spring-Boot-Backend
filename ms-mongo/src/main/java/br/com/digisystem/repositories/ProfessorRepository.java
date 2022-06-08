package br.com.digisystem.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.digisystem.entities.ProfessorEntity;

public interface ProfessorRepository extends MongoRepository<ProfessorEntity, String>{

}
