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
	private UserRepository userRepository;
	
	public List<UserEntity> findAll() {
		
		return this.userRepository.findAll();
	}
	
	public UserEntity findOne(int id) {
		
		return this.userRepository.findById(id).orElseThrow( () -> new ObjNotFoundException("Element with ID " + id + " not found") );
	}
	
	public UserEntity create(UserEntity user) {
		
		UserEntity newUser = new UserEntity();
		if (user.getName() == null || user.getEmail() == null){
			return null;
		}
		
		newUser.setEmail(user.getEmail());
		newUser.setName(user.getName());
		
		return this.userRepository.save(newUser);
	}
	
	public UserEntity update(int id, UserEntity user) {
		
		Optional<UserEntity> userReturn = userRepository.findById(id);
		if(userReturn.isPresent()){
			UserEntity userUpdate = userReturn.get();
			
			userUpdate.setEmail(user.getEmail());
			userUpdate.setName(user.getName());
			
			return this.userRepository.save(userUpdate);
		}else {
			return null;
		}
	}
	
	public void delete(int id) {
		this.userRepository.deleteById(id);
	}
	
	public List<UserEntity> getByName(String name){
		return this.userRepository.searchByName(name);
	}
}
