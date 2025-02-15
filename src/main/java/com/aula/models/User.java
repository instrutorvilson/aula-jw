package com.aula.models;

public class User {
	private Long Id;
	private String nome;
	private String email;
	private String password;

	
	public User() {
	}

	public User(Long id, String nome, String email, String password) {
		Id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
