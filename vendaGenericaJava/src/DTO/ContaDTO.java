package DTO;

public class ContaDTO {
	private String senha;
	private String nome;
	private String email;
	private String telefone;
	private String ulrImagemPerfil;

	public String getUlrImagemPerfil() {
		return ulrImagemPerfil;
	}

	public void setUlrImagemPerfil(String ulrImagemPerfil) {
		this.ulrImagemPerfil = ulrImagemPerfil;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
