package br.com.digisystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.digisystem.entities.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
	
	public List<UsuarioEntity> findByNomeContains(String nome);
	
	public List<UsuarioEntity> findByNomeStartsWith(String nome);
	
	public List<UsuarioEntity> findByNomeEndsWith(String nome);
	
	public List<UsuarioEntity> findByNomeContainsAndEmailContains(String nome, String email);
	
	//JPQL (Java Persistence Query Language)
	@Query("SELECT u FROM UsuarioEntity u WHERE u.nome LIKE %:nome%")
	public List<UsuarioEntity> searchByNome(@Param("nome") String nome);
	
	@Query(value = "SELECT * FROM usuarios u WHERE u.nome LIKE %:nome%",
			nativeQuery = true)
	public List<UsuarioEntity> searchByNomeNativo(@Param("nome") String nome);
}
