package DTO;

import java.util.ArrayList;

public class CarrinhoDTO{
	private ArrayList<ProdutoDTO> produtos;
	private ClienteDTO cliente;
	private String nome;
	
	public ArrayList<ProdutoDTO> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
