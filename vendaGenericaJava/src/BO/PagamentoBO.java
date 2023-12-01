package BO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DAO.CarrinhoProdutoDAO;
import DAO.PagamentoDAO;
import DAO.ProdutoPagoDAO;
import DTO.CarrinhoDTO;
import DTO.CarrinhoProdutoDTO;
import DTO.PagamentoDTO;
import DTO.ProdutoDTO;
import DTO.ProdutoPagoDTO;
import ENUMS.FormaPagamento;
import ENUMS.StatusPagamento;

public class PagamentoBO {
	public static List<PagamentoDTO> getByCarrinho(int id){
		PagamentoDAO pagdao = new PagamentoDAO();
		return pagdao.getByCarrinho(id);
	}
	public static List<PagamentoDTO> getLast(int id) {
		PagamentoDAO pagamentodao = new PagamentoDAO();
		return pagamentodao.getLastByCarrinho(id);
	}
	public static String cadastrarPagamento(int id, String forma) {
		PagamentoDTO pagamento = new PagamentoDTO();
		pagamento.setCarrinho(id);
		pagamento.setFormaPagamento(FormaPagamento.valueOf(forma));
		pagamento.setData(LocalDate.now());
		pagamento.setStatus(StatusPagamento.FINALIZADO);
		
		CarrinhoProdutoDTO carrinho = new CarrinhoProdutoDTO();
		CarrinhoProdutoDAO carrinhodao = new CarrinhoProdutoDAO();
		carrinho = carrinhodao.getByCarrinho(id).get(0);
		
		List<ProdutoDTO> produtos = ProdutoBO.obterPorId(carrinho);
		PagamentoDAO pagamentodao = new PagamentoDAO();
		pagamentodao.create(pagamento);
		pagamento=PagamentoBO.getLast(id).get(0);

		ProdutoPagoDAO produtopagoDao = new ProdutoPagoDAO();
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
