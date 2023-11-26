package DTO;

import java.util.ArrayList;

public class CarrinhoDTO{
	private ArrayList<Integer> produtos;
	private ArrayList<Integer> quantidades;
	private int cliente;
	private String nome;
	private int id;
	
	public ArrayList<Integer> getQuantidades() {
		return quantidades;
	}
	public void setQuantidades(ArrayList<Integer> quantidades) {
		this.quantidades = quantidades;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Integer> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<Integer> produtos) {
		this.produtos = produtos;
	}
	public int getCliente() {
		return cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
