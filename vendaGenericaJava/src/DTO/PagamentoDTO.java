package DTO;

import ENUMS.StatusPagamento;
import ENUMS.FormaPagamento;

import java.time.LocalDate;
import java.util.ArrayList;

public class PagamentoDTO {
	private double valor;
	private FormaPagamento formaPagamento;
	private StatusPagamento status = StatusPagamento.PENDENTE;
	private ArrayList<String> produtosNome;
	private ArrayList<Double> produtosPreco;
	private ArrayList<Integer> produtosQuantidade;
	private LocalDate data;
	private int carrinho;
	private int id;
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public int getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(int carrinho) {
		this.carrinho = carrinho;
	}
	public ArrayList<Integer> getProdutosQuantidade() {
		return produtosQuantidade;
	}
	public void setProdutosQuantidade(ArrayList<Integer> produtosQuantidade) {
		this.produtosQuantidade = produtosQuantidade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public StatusPagamento getStatus() {
		return status;
	}
	public void setStatus(StatusPagamento status) {
		this.status = status;
	}
	public ArrayList<String> getProdutosNome() {
		return produtosNome;
	}
	public void setProdutosNome(ArrayList<String> produtosNome) {
		this.produtosNome = produtosNome;
	}
	public ArrayList<Double> getProdutosPreco() {
		return produtosPreco;
	}
	public void setProdutosPreco(ArrayList<Double> produtosPreco) {
		this.produtosPreco = produtosPreco;
	}
	
}
