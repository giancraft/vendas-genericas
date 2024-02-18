package BO;

import java.util.List;

import DAO.PagamentoDAO;
import DAO.ProdutoDAO;
import DAO.ProdutoPagoDAO;
import DTO.CarrinhoDTO;
import DTO.CarrinhoProdutoDTO;
import DTO.PagamentoDTO;
import DTO.ProdutoDTO;
import DTO.ProdutoPagoDTO;

public class CarrinhoBO extends BO{
	
	public static String deletarCarrinhoProd(CarrinhoProdutoDTO carrinho) {
		return carrinhoR.delete(carrinho)?"Removido do carrinho":"Tente novamente";
	}
	public static boolean isProdutoCadastrado(ProdutoDTO produto) {
		return carrinhoR.getByProduto(produto).size()>0;
	}
	public static String adicionarCarrinhoProduto(CarrinhoProdutoDTO carrinhon) {
		return carrinhoR.create(carrinhon)?"Adicionado ao carrinho":"Tente novamente";
	}
	public static List<CarrinhoProdutoDTO> getProdutoCadastrado(ProdutoDTO produto) {
		return carrinhoR.getByProduto(produto);
	}
	public static String alterarCarrinho(CarrinhoProdutoDTO carrinho) {
		return carrinhoR.update(carrinho)?"Quantidade alterada!":"Erro";
	}
	public static String deletarCarrinho(CarrinhoDTO carrinho) {
		List<CarrinhoProdutoDTO> carrinhospdtos = carrinhoR.getByCarrinho(carrinho.getId());
		for(int i = 0; i<carrinhospdtos.size(); i++) {
			carrinhoR.delete(carrinhospdtos.get(i));
		}
		PagamentoDAO pagamentodao = new PagamentoDAO();
		List<PagamentoDTO> pagamentos = pagamentodao.getByCarrinho(carrinho.getId());
		ProdutoPagoDAO produtospagosdao = new ProdutoPagoDAO();
		for(int i = 0; i<pagamentos.size(); i++) {
			List<ProdutoPagoDTO> produtosPagos = produtospagosdao.getByCarrinho(carrinho.getId());
			for(int j =0; j<produtosPagos.size();j++) {
				produtospagosdao.delete(produtosPagos.get(j));
			}
			pagamentodao.delete(pagamentos.get(i));
		}
		return carrinhodao.delete(carrinho) ? "Carrinho deletado com sucesso":"Ocorreu um erro, tente novamente";
	}
	public static String alterarCarrinho(CarrinhoDTO carrinho) {
		return carrinhodao.update(carrinho) ? "Carrinho alterado com sucesso":"Ocorreu um erro, tente novamente";
	}
	public static List<CarrinhoDTO> listarCarrinhoCliente(int idCliente){
		return carrinhodao.getByCliente(idCliente);
	}
	public static List<CarrinhoDTO> getByNome(String nome) {
		return carrinhodao.getByName(nome);
	}
	public static String criarCarrinho(CarrinhoDTO carrinho) {
		return carrinhodao.create(carrinho)? "Carrinho criado com sucesso " : "Ocorreu um erro";
	}
	public static List<CarrinhoProdutoDTO> mostrarProdutos(int id){
		List<CarrinhoProdutoDTO> carrinhos = carrinhoR.getByCarrinho(id);
		for(int i=0; i<carrinhos.size(); i++) {
			ProdutoDAO produtodao = new ProdutoDAO();
			ProdutoDTO produto = new ProdutoDTO();
			produto.setId(carrinhos.get(i).getIdProduto());
			produto = produtodao.find(produto).get(0);
			carrinhos.get(i).setNome(produto.getNome());
		}
		return carrinhos;
	}
}
