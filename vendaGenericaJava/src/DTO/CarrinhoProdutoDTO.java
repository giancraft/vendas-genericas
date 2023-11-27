package DTO;

public class CarrinhoProdutoDTO {
	private int idCarrinho;
	private int idProduto;
	private int quantidade;
	
	public int getIdCarrinho() {
		return idCarrinho;
	}
	public void setIdCarrinho(int idCarrinho) {
		this.idCarrinho = idCarrinho;
	}
	
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidades) {
		this.quantidade = quantidades;
	}
	
}
