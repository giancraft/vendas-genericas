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
		return permanencia.getCarrinhoR().delete(carrinho)?"Removido do carrinho":"Tente novamente";
	}
	public boolean isProdutoCadastrado(ProdutoDTO produto) {
		return permanencia.getCarrinhoR().getByProduto(produto).size()>0;
	}
	public String adicionarCarrinhoProduto(CarrinhoProdutoDTO carrinhon) {
		return permanencia.getCarrinhoR().create(carrinhon)?"Adicionado ao carrinho":"Tente novamente";
	}
	public List<CarrinhoProdutoDTO> getProdutoCadastrado(ProdutoDTO produto) {
		return permanencia.getCarrinhoR().getByProduto(produto);
	}
	public String alterarCarrinho(CarrinhoProdutoDTO carrinho) {
		return permanencia.getCarrinhoR().update(carrinho)?"Quantidade alterada!":"Erro";
	}
	public String deletarCarrinho(CarrinhoDTO carrinho) {
		List<CarrinhoProdutoDTO> carrinhospdtos = permanencia.getCarrinhoR().getByCarrinho(carrinho.getId());
		for(int i = 0; i<carrinhospdtos.size(); i++) {
			permanencia.getCarrinhoR().delete(carrinhospdtos.get(i));
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
		return permanencia.getCarrinhodao().delete(carrinho) ? "Carrinho deletado com sucesso":"Ocorreu um erro, tente novamente";
	}
	public String alterarCarrinho(CarrinhoDTO carrinho) {
		return permanencia.getCarrinhodao().update(carrinho) ? "Carrinho alterado com sucesso":"Ocorreu um erro, tente novamente";
	}
	public List<CarrinhoDTO> listarCarrinhoCliente(int idCliente){
		return permanencia.getCarrinhodao().getByCliente(idCliente);
	}
	public List<CarrinhoDTO> getByNome(String nome) {
		return permanencia.getCarrinhodao().getByName(nome);
	}
	public String criarCarrinho(CarrinhoDTO carrinho) {
		return permanencia.getCarrinhodao().create(carrinho)? "Carrinho criado com sucesso " : "Ocorreu um erro";
	}
	public List<CarrinhoProdutoDTO> mostrarProdutos(int id){
		List<CarrinhoProdutoDTO> carrinhos = permanencia.getCarrinhoR().getByCarrinho(id);
		for(int i=0; i<carrinhos.size(); i++) {
			ProdutoDTO produto = new ProdutoDTO();
			produto.setId(carrinhos.get(i).getIdProduto());
			produto = permanencia.getProdutodao().find(produto).get(0);
			carrinhos.get(i).setNome(produto.getNome());
		}
		return carrinhos;
	}
}
