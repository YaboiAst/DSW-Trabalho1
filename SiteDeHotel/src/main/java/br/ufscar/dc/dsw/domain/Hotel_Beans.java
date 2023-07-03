package br.ufscar.dc.dsw.domain;

public class Hotel_Beans extends Cliente_Beans {
	private String cnpj;
	private String cidade;
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Hotel_Beans() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Hotel_Beans(String email, String senha, String nome, String cnpj, String cidade) {
		super(email, senha, nome);
		this.cnpj = cnpj;
		this.cidade = cidade;
	}

}
