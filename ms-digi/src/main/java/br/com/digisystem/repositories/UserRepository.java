package br.com.digisystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.digisystem.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	public List<UserEntity> findByNomeContains(String nome);
	
	public List<UserEntity> findByNomeStartsWith(String nome);
	
	public List<UserEntity> findByNomeEndsWith(String nome);
	
	public List<UserEntity> findByNomeContainsAndEmailContains(String nome, String email);
	
	//JPQL (Java Persistence Query Language)
	@Query("SELECT u FROM UserEntity u WHERE u.nome LIKE %:nome%")
	public List<UserEntity> searchByNome(@Param("nome") String nome);
	
	@Query(value = "SELECT * FROM usuarios u WHERE u.nome LIKE %:nome%",
			nativeQuery = true)
	public List<UserEntity> searchByNomeNativo(@Param("nome") String nome);
}
