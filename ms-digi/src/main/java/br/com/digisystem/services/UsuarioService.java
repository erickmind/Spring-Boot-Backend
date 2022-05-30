package br.com.digisystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digisystem.entities.UsuarioEntity;
import br.com.digisystem.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioEntity> findAll() {
		
		return this.usuarioRepository.findAll();
	}
	
	public UsuarioEntity find(int id) {
		
		Optional<UsuarioEntity> optional = this.usuarioRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	public UsuarioEntity create(UsuarioEntity usuario) {
		
		UsuarioEntity newUser = new UsuarioEntity();
		if (usuario.getNome() == null || usuario.getEmail() == null){
			return null;
		}
		
		newUser.setEmail(usuario.getEmail());
		newUser.setNome(usuario.getNome());
		
		return this.usuarioRepository.save(newUser);
	}
	
	public UsuarioEntity update(int id, UsuarioEntity usuario) {
		
		Optional<UsuarioEntity> user = usuarioRepository.findById(id);
		if(user.isPresent()){
			UsuarioEntity usuarioUpdate = user.get();
			
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
	
	public List<UsuarioEntity> getByNome(String nome){
		return this.usuarioRepository.searchByNome(nome);
	}
}
