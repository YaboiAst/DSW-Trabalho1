package br.ufscar.dc.dsw.domain;

public class Cliente_Beans {
	private String email;
	private String senha;
	private String nome;
	private String papel;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getPapel() {
		return papel;
	}
	
	public void setPapel(String papel) {
		this.papel = papel;
	}
	
	public Cliente_Beans() {
		super();
	}
	
	public Cliente_Beans(String email, String senha, String nome) {
		super();
		this.email = email;
		this.senha = senha;
		this.nome = nome;
	}
	
	public Cliente_Beans(String email, String senha, String nome, String papel) {
		super();
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.papel = papel;
	}
}

