package br.com.digisystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.digisystem.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	public List<UserEntity> findByNameContains(String name);
	
	public List<UserEntity> findByNameStartsWith(String name);
	
	public List<UserEntity> findByNameEndsWith(String name);
	
	public List<UserEntity> findByNameContainsAndEmailContains(String name, String email);
	
	//JPQL (Java Persistence Query Language)
	@Query("SELECT u FROM UserEntity u WHERE u.name LIKE %:name%")
	public List<UserEntity> searchByName(@Param("name") String name);
	
	@Query(value = "SELECT * FROM users u WHERE u.name LIKE %:name%",
			nativeQuery = true)
	public List<UserEntity> searchByNameNativo(@Param("name") String name);
	
	@Modifying
	@Query(value = "UPDATE users SET NAME = :name WHERE ID = :id", nativeQuery=true)
	public void updateUser(@Param("id") int id, @Param("name") String nome);
}
