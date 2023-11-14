package classes;

import java.util.ArrayList;

public class Pagamento {
	private double valor;
	private FormaPagamento formaPagamento;
	private StatusPagamento status = StatusPagamento.PENDENTE;
	private ArrayList<String> produtosNome;
	private ArrayList<Double> produtosPreco;
	
	public void Pagamento(ArrayList<Carrinho> carrinho) {
		
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
