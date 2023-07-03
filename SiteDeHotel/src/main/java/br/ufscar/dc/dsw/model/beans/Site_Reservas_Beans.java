package model;

public class Site_Reservas_Beans extends Cliente_Beans {
	private String url;
	private String telefone;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Site_Reservas_Beans() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Site_Reservas_Beans(String email, String senha, String nome, String url, String telefone) {
		super(email, senha, nome);
		this.url = url;
		this.telefone = telefone;
	}

	
	
	
}
