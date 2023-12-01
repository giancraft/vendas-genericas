package BO;

import java.util.List;

import DAO.CarrinhoDAO;
import DAO.CarrinhoProdutoDAO;
import DAO.PagamentoDAO;
import DAO.ProdutoDAO;
import DTO.CarrinhoDTO;
import DTO.CarrinhoProdutoDTO;
import DTO.PagamentoDTO;
import DTO.ProdutoDTO;

public class CarrinhoBO {
	public static String deletarCarrinhoProd(CarrinhoProdutoDTO carrinho) {
		CarrinhoProdutoDAO carrinhoR = new CarrinhoProdutoDAO();
		return carrinhoR.delete(carrinho)?"Removido do carrinho":"Tente novamente";
	}
	public static boolean isProdutoCadastrado(ProdutoDTO produto) {
		CarrinhoProdutoDAO carrinhoR = new CarrinhoProdutoDAO();
		CarrinhoProdutoDTO carrinho = new CarrinhoProdutoDTO();
		return carrinhoR.getByProduto(produto).size()>0;
	}
	public static String adicionarCarrinhoProduto(CarrinhoProdutoDTO carrinhon) {
		CarrinhoProdutoDAO carrinhoR = new CarrinhoProdutoDAO();
		return carrinhoR.create(carrinhon)?"Adicionado ao carrinho":"Tente novamente";
	}
	public static List<CarrinhoProdutoDTO> getProdutoCadastrado(ProdutoDTO produto) {
		CarrinhoProdutoDAO carrinhoR = new CarrinhoProdutoDAO();
		CarrinhoProdutoDTO carrinho = new CarrinhoProdutoDTO();
		return carrinhoR.getByProduto(produto);
	}
	public static String alterarCarrinho(CarrinhoProdutoDTO carrinho) {
		CarrinhoProdutoDAO carrinhoR = new CarrinhoProdutoDAO();
		return carrinhoR.update(carrinho)?"Quantidade alterada!":"Erro";
	}
	public static String deletarCarrinho(CarrinhoDTO carrinho) {
		CarrinhoDAO carrinhodao = new CarrinhoDAO();
		CarrinhoProdutoDAO carrinhopdao = new CarrinhoProdutoDAO();
		List<CarrinhoProdutoDTO> carrinhospdtos = carrinhopdao.getByCarrinho(carrinho.getId());
		for(int i = 0; i<carrinhospdtos.size(); i++) {
			carrinhopdao.delete(carrinhospdtos.get(i));
		}
		PagamentoDAO pagamentodao = new PagamentoDAO();
		List<PagamentoDTO> pagamentos = pagamentodao.getByCarrinho(carrinho.getId());
		for(int i = 0; i<pagamentos.size(); i++) {
			pagamentodao.delete(pagamentos.get(i));
		}
		return carrinhodao.delete(carrinho) ? "Carrinho deletado com sucesso":"Ocorreu um erro, tente novamente";
	}
	public static String alterarCarrinho(CarrinhoDTO carrinho) {
		CarrinhoDAO carrinhodao = new CarrinhoDAO();
		return carrinhodao.update(carrinho) ? "Carrinho alterado com sucesso":"Ocorreu um erro, tente novamente";
	}
	public static List<CarrinhoDTO> listarCarrinhoCliente(int idCliente){
		CarrinhoDAO carrinhodao = new CarrinhoDAO();
		return carrinhodao.getByCliente(idCliente);
	}
	public static List<CarrinhoDTO> getByNome(String nome) {
		CarrinhoDAO carrinhodao = new CarrinhoDAO();
		return carrinhodao.getByName(nome);
	}
	public static String criarCarrinho(CarrinhoDTO carrinho) {
		CarrinhoDAO carrinhodao = new CarrinhoDAO();
		return carrinhodao.create(carrinho)? "Carrinho criado com sucesso " : "Ocorreu um erro";
	}
	public static List<CarrinhoProdutoDTO> mostrarProdutos(int id){
		CarrinhoProdutoDAO carrinhoR = new CarrinhoProdutoDAO();
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
