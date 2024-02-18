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
	public static List<PagamentoDTO> getByData(LocalDate inicio, LocalDate fim){
		return pagdao.getByData(inicio, fim);
	}
	public static List<PagamentoDTO> getByCarrinho(int id){
		return pagdao.getByCarrinho(id);
	}
	public static List<PagamentoDTO> getLast(int id) {
		return pagdao.getLastByCarrinho(id);
	}
	public static String cadastrarPagamento(int id, String forma) {
		PagamentoDTO pagamento = new PagamentoDTO();
		pagamento.setCarrinho(id);
		pagamento.setFormaPagamento(FormaPagamento.valueOf(forma));
		pagamento.setData(LocalDate.now());
		pagamento.setStatus(StatusPagamento.FINALIZADO);
		
		CarrinhoProdutoDTO carrinho = new CarrinhoProdutoDTO();
		carrinho = carrinhoR.getByCarrinho(id).get(0);
		
		List<ProdutoDTO> produtos = ProdutoBO.obterPorId(carrinho);
		pagdao.create(pagamento);
		pagamento=PagamentoBO.getLast(id).get(0);

		ProdutoPagoDTO produtoPago = new ProdutoPagoDTO();
		
		for(int i = 0; i<produtos.size(); i++) {
			produtoPago.setIdPagamento(pagamento.getId());
			produtoPago.setNome(produtos.get(i).getNome());
			produtoPago.setPreco(produtos.get(i).getPreco());
			produtoPago.setQuantidade(CarrinhoBO.getProdutoCadastrado(produtos.get(i)).get(0).getQuantidade());
			produtopagoDao.create(produtoPago);
		}
		
		return "Pagamento realizado";
	}
}
