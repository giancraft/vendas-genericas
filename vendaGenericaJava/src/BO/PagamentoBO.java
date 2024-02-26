package BO;

import java.time.LocalDate;
import java.util.List;

import DTO.CarrinhoProdutoDTO;
import DTO.PagamentoDTO;
import DTO.ProdutoDTO;
import DTO.ProdutoPagoDTO;
import ENUMS.FormaPagamento;
import ENUMS.StatusPagamento;

public class PagamentoBO extends BO {
	public List<PagamentoDTO> getByData(LocalDate inicio, LocalDate fim){
		return permanencia.pagdao.getByData(inicio, fim);
	}
	public List<PagamentoDTO> getByCarrinho(int id){
		return permanencia.pagdao.getByCarrinho(id);
	}
	public List<PagamentoDTO> getLast(int id) {
		return permanencia.pagdao.getLastByCarrinho(id);
	}
	public String cadastrarPagamento(int id, String forma) {
		PagamentoDTO pagamento = new PagamentoDTO();
		pagamento.setCarrinho(id);
		pagamento.setFormaPagamento(FormaPagamento.valueOf(forma));
		pagamento.setData(LocalDate.now());
		pagamento.setStatus(StatusPagamento.FINALIZADO);
		
		CarrinhoProdutoDTO carrinho = new CarrinhoProdutoDTO();
		carrinho = permanencia.carrinhoR.getByCarrinho(id).get(0);
		ProdutoBO produtoBo = new ProdutoBO();
		List<ProdutoDTO> produtos = produtoBo.obterPorId(carrinho);
		permanencia.pagdao.create(pagamento);
		pagamento=this.getLast(id).get(0);

		ProdutoPagoDTO produtoPago = new ProdutoPagoDTO();
		
		for(int i = 0; i<produtos.size(); i++) {
			produtoPago.setIdPagamento(pagamento.getId());
			produtoPago.setNome(produtos.get(i).getNome());
			produtoPago.setPreco(produtos.get(i).getPreco());
			CarrinhoBO carrinhoBo = new CarrinhoBO();
			produtoPago.setQuantidade(carrinhoBo.getProdutoCadastrado(produtos.get(i)).get(0).getQuantidade());
			permanencia.produtopagoDao.create(produtoPago);
		}
		
		return "Pagamento realizado";
	}
}
