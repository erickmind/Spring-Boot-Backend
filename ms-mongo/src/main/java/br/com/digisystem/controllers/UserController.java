package br.com.digisystem.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.digisystem.dtos.UserDTO;
import br.com.digisystem.entities.UserEntity;
import br.com.digisystem.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("users")
	@ApiOperation(value="List all Users")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=400, message="Bad Request")
	})
	public ResponseEntity<List<UserDTO>> getAll() {
		
		log.info("GET ALL Users");
		
		List<UserEntity> userList = this.userService.findAll();
		List<UserDTO> dtoList = userList.stream().map(UserEntity::toDTO).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dtoList);
	}
	
	@ApiOperation(value="Get one User by ID")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=400, message="Bad Request")
	})
	public ResponseEntity<UserDTO> get(@PathVariable String id) {
		
		return ResponseEntity.ok().body(userService.findOne(id).toDTO());
	}
	
	@PostMapping("users")
	@ApiOperation(value="Create a new User")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=400, message="Bad Request")
	})
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) {
		
		UserEntity user = dto.toEntity();
		UserEntity userResponse = this.userService.create(user);
		
		return ResponseEntity.ok().body(userResponse.toDTO());
	}
	
	@PatchMapping("users/{id}")
	@ApiOperation(value="Update an User")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=400, message="Bad Request")
	})
	public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO dto) {
		
		UserEntity user = dto.toEntity();
		UserEntity userResponse = this.userService.update(id, user);
		
		return ResponseEntity.ok().body(userResponse.toDTO());
	}
	
	@DeleteMapping("users/{id}")
	@ApiOperation(value="Delete one User by ID")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=400, message="Bad Request")
	})
	public ResponseEntity<Void> delete(@PathVariable String id) {
	
		this.userService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("users/get-by-name/{name}")
	@ApiOperation(value="Get one User by Name")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=400, message="Bad Request")
	})
	public ResponseEntity<List<UserDTO>> getByName(@PathVariable String name){
		
		List<UserEntity> userList = this.userService.getByName(name);
		List<UserDTO> userListDTO = userList.stream().map(UserEntity::toDTO).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(userListDTO);
	}
	
	@PatchMapping("users/update/{id}")
	@ApiOperation(value="Update User by ID")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=400, message="Bad Request")
	})
	public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO dto){
		
		return ResponseEntity.ok().body(this.userService.updateUser(id, dto.getName()).toDTO());
	}
	
	@GetMapping("users/pagination")
	public ResponseEntity<Page<UserDTO>> getAllPagination(
			@RequestParam( name="page", defaultValue = "0") int page,
			@RequestParam( name="limit", defaultValue = "10") int limit
	){
		log.info("page = {}, limit = {}", page, limit);
		return ResponseEntity.ok().body(userService.findAllPagination(page, limit));
	}
}	