package com.aula.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.models.Local;
import com.aula.repositories.LocalRepository;
import com.aula.utils.Erros;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/locais")
public class LocalController {
    @Autowired
	LocalRepository repository;
	
	@GetMapping
	public ResponseEntity<Object> GetAll() {
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> GetOne(@PathVariable("id") Long id) {
		Optional<Local> localExistente = repository.findById(id);
		if(localExistente.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(localExistente.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Erros(404, "Local não encontrado."));
	}
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody Local local) {
		Local novoLocal = repository.save(local);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoLocal);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Local local) {
		Optional<Local> opt = repository.findById(id);
		if(opt.isPresent()) {
			Local localExistente = opt.get();
			localExistente.setDescricao(local.getDescricao());
			localExistente.setRua(local.getRua());
			repository.save(localExistente);
			return ResponseEntity.status(HttpStatus.OK).body(localExistente);
		}
		Erros erro = new Erros(404, "Local não encontrado.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		Optional<Local> opt = repository.findById(id);
		if(opt.isPresent()) {
			repository.delete(opt.get());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Erros(404, "Local não encontrado."));
	}
}
