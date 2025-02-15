package com.aula.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.models.User;

@RestController
@RequestMapping("api/v2/users")
public class UserControllerStatusCorreto {
	static List<User> usuarios = new ArrayList<User>();

	public UserControllerStatusCorreto() {
		usuarios.add(new User(1L,"ana","ana@gmail.com","123"));
		usuarios.add(new User(2L,"maria","maria@gmail.com","123"));
		usuarios.add(new User(3L,"Antonio","antonio@gmail.com","123"));
	}
	
	@GetMapping // http://localhost:8080/api/v2/users
	public ResponseEntity<Object> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}
	
	@GetMapping("{id}")// http://localhost:8080/api/v2/users/2
	public ResponseEntity<Object> getOne(@PathVariable("id") Long id){
		User userExistente = usuarios.stream()
				.filter(u -> u.getId() == id)
				.findFirst()
				.orElse(null);
		if(userExistente != null){
			return ResponseEntity.status(HttpStatus.OK).body(userExistente);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
	}
	
	@PostMapping //http://localhost:8080/api/v2/users
	public ResponseEntity<Object> Save(@RequestBody User user) {
		user.setId(usuarios.size()+1L);
		usuarios.add(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@PutMapping("/{id}") // http://localhost:8080/api/v2/users/1
	public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody User user){
	   User userExistente = usuarios.stream()
				.filter(u -> u.getId() == id)
				.findFirst()
				.orElse(null);
	   if(userExistente != null) {
		   userExistente.setNome(user.getNome());
		   userExistente.setEmail(user.getEmail());
		   userExistente.setPassword(user.getPassword());
		   return ResponseEntity.status(HttpStatus.OK).body(userExistente);
	   }
	   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
	}
	
	@DeleteMapping("/{id}") // http://localhost:8080/api/v1/users/1
	public ResponseEntity<Object> delete(@PathVariable("id") Long id){
	   User userExistente = usuarios.stream()
				.filter(u -> u.getId() == id)
				.findFirst()
				.orElse(null);
	   if(userExistente != null) {
		   usuarios.remove(userExistente);
		   return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	   }
	   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
	}
}
