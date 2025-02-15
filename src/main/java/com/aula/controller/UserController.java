package com.aula.controller;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/api/v1/users")
public class UserController {
    static List<User> usuarios = new ArrayList<User>();

	public UserController() {
		usuarios.add(new User(1L,"ana","ana@gmail.com","123"));
		usuarios.add(new User(2L,"maria","maria@gmail.com","123"));
		usuarios.add(new User(3L,"Antonio","antonio@gmail.com","123"));
	}
	@DeleteMapping("/{id}") // http://localhost:8080/api/v1/users/1
	public String delete(@PathVariable("id") Long id){
	   User userExistente = usuarios.stream()
				.filter(u -> u.getId() == id)
				.findFirst()
				.orElse(null);
	   if(userExistente != null) {
		   usuarios.remove(userExistente);
		   return "Usuário excluido com sucesso";
	   }
		return "Usuário não encontrado";
	}
	
	@PutMapping("/{id}") // http://localhost:8080/api/v1/users/1
	public String update(@PathVariable("id") Long id, @RequestBody User user){
	   User userExistente = usuarios.stream()
				.filter(u -> u.getId() == id)
				.findFirst()
				.orElse(null);
	   if(userExistente != null) {
		   userExistente.setNome(user.getNome());
		   userExistente.setEmail(user.getEmail());
		   userExistente.setPassword(user.getPassword());
		   return userExistente.toString();
	   }
		return "Usuário não encontrado";
	}
	
	@GetMapping // http://localhost:8080/api/v1/users
	public List<User> getAll(){
		return usuarios;
	}
	
	@GetMapping("/{id}") // http://localhost:8080/api/v1/users/1
	public User getOne(@PathVariable("id") Long id){
	  /*	return usuarios.stream()
				.filter(u -> u.getId() == id)
				.findFirst()
				.orElse(null);*/
		for(int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getId() == id) {
				return usuarios.get(i);
			}
		}
		return null;
	}
		
	@PostMapping //http://localhost:8080/api/v1/users
	public User Save(@RequestBody User user) {
		user.setId(usuarios.size()+1L);
		usuarios.add(user);
		return user;
	}
}
