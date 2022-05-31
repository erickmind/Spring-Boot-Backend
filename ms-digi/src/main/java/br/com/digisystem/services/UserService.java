package br.com.digisystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digisystem.entities.UserEntity;
import br.com.digisystem.exceptions.ObjNotFoundException;
import br.com.digisystem.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository usuarioRepository;
	
	public List<UserEntity> findAll() {
		
		return this.usuarioRepository.findAll();
	}
	
	public UserEntity findOne(int id) {
		
		return this.usuarioRepository.findById(id).orElseThrow( () -> new ObjNotFoundException("Element with ID " + id + " not found") );
	}
	
	public UserEntity create(UserEntity usuario) {
		
		UserEntity newUser = new UserEntity();
		if (usuario.getNome() == null || usuario.getEmail() == null){
			return null;
		}
		
		newUser.setEmail(usuario.getEmail());
		newUser.setNome(usuario.getNome());
		
		return this.usuarioRepository.save(newUser);
	}
	
	public UserEntity update(int id, UserEntity usuario) {
		
		Optional<UserEntity> user = usuarioRepository.findById(id);
		if(user.isPresent()){
			UserEntity usuarioUpdate = user.get();
			
			usuarioUpdate.setEmail(usuario.getEmail());
			usuarioUpdate.setNome(usuario.getNome());
			
			return this.usuarioRepository.save(usuarioUpdate);
		}else {
			return null;
		}
	}
	
	public void delete(int id) {
		this.usuarioRepository.deleteById(id);
	}
	
	public List<UserEntity> getByNome(String nome){
		return this.usuarioRepository.searchByNome(nome);
	}
}
