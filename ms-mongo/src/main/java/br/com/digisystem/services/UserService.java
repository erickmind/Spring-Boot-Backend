package br.com.digisystem.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.digisystem.dtos.UserDTO;
import br.com.digisystem.entities.UserEntity;
import br.com.digisystem.exceptions.ObjNotFoundException;
import br.com.digisystem.repositories.CustomRepository;
import br.com.digisystem.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomRepository customRepository;
	
	public List<UserEntity> findAll() {
		
		return this.userRepository.findAll();
	}
	
	public UserEntity findOne(String id) {
		
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
	
	public UserEntity update(String id, UserEntity user) {
		
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
	
	public void delete(String id) {
		try {
			this.userRepository.deleteById(id);
		} catch (Exception e){
			log.error("Deleting error for User with ID: " + id + ". Error " + e.getMessage() );
		}
	}
	
	public List<UserEntity> getByName(String name){
		return this.userRepository.searchByName(name);
	}
	
	public UserEntity updateUser(String id, String name) {
		return this.customRepository.updateUser(id, name);
	}
	
	public Page<UserDTO> findAllPagination(int page, int limit){
		PageRequest pageReq = PageRequest.of(page, limit);
		
		Page<UserEntity> userPage = userRepository.findAll( pageReq );
		
		Page<UserDTO> dtoPage = userPage.map(
				new Function<UserEntity, UserDTO> (){
					public UserDTO apply(UserEntity entity) {
						return entity.toDTO();
					} 
				}
			);
		
		return dtoPage;
	}
}