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
	
	public String deletarCarrinhoProd(CarrinhoProdutoDTO carrinho) {
		return permanencia.carrinhoR.delete(carrinho)?"Removido do carrinho":"Tente novamente";
	}
	public boolean isProdutoCadastrado(ProdutoDTO produto) {
		return permanencia.carrinhoR.getByProduto(produto).size()>0;
	}
	public String adicionarCarrinhoProduto(CarrinhoProdutoDTO carrinhon) {
		return permanencia.carrinhoR.create(carrinhon)?"Adicionado ao carrinho":"Tente novamente";
	}
	public List<CarrinhoProdutoDTO> getProdutoCadastrado(ProdutoDTO produto) {
		return permanencia.carrinhoR.getByProduto(produto);
	}
	public String alterarCarrinho(CarrinhoProdutoDTO carrinho) {
		return permanencia.carrinhoR.update(carrinho)?"Quantidade alterada!":"Erro";
	}
	public String deletarCarrinho(CarrinhoDTO carrinho) {
		List<CarrinhoProdutoDTO> carrinhospdtos = permanencia.carrinhoR.getByCarrinho(carrinho.getId());
		for(int i = 0; i<carrinhospdtos.size(); i++) {
			permanencia.carrinhoR.delete(carrinhospdtos.get(i));
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
		return permanencia.carrinhodao.delete(carrinho) ? "Carrinho deletado com sucesso":"Ocorreu um erro, tente novamente";
	}
	public String alterarCarrinho(CarrinhoDTO carrinho) {
		return permanencia.carrinhodao.update(carrinho) ? "Carrinho alterado com sucesso":"Ocorreu um erro, tente novamente";
	}
	public List<CarrinhoDTO> listarCarrinhoCliente(int idCliente){
		return permanencia.carrinhodao.getByCliente(idCliente);
	}
	public List<CarrinhoDTO> getByNome(String nome) {
		return permanencia.carrinhodao.getByName(nome);
	}
	public String criarCarrinho(CarrinhoDTO carrinho) {
		return permanencia.carrinhodao.create(carrinho)? "Carrinho criado com sucesso " : "Ocorreu um erro";
	}
	public List<CarrinhoProdutoDTO> mostrarProdutos(int id){
		List<CarrinhoProdutoDTO> carrinhos = permanencia.carrinhoR.getByCarrinho(id);
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
