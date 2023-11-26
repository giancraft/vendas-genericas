package VIEW;

import java.time.LocalDate;
import java.util.ArrayList;

import DAO.CarrinhoDAO;
import DAO.MarcaDAO;
import DAO.PagamentoDAO;
import DAO.ProdutoDAO;
import DTO.CarrinhoDTO;
import DTO.ContaDTO;
import DTO.MarcaDTO;
import DTO.PagamentoDTO;
import DTO.ProdutoDTO;
import ENUMS.FormaPagamento;
import ENUMS.StatusPagamento;

public class mainView {

	public static void main(String[] args) {
		ContaDTO conta = new ContaDTO();
		conta.setNome("teste");
		conta.setEmail("teste@");
		conta.setTelefone("8888888");
		conta.setSenha("999999999");
		conta.setId(1);
		ArrayList<Integer> produtos = new ArrayList<Integer>();
		produtos.add(1);
		produtos.add(2);
		CarrinhoDTO carrinho = new CarrinhoDTO();
		carrinho.setNome("teste3");
		carrinho.setCliente(conta.getId());
		carrinho.setId(2);
		carrinho.setProdutos(produtos);
		carrinho.setQuantidades(produtos);
		
		MarcaDTO marca = new MarcaDTO();
		marca.setNome("Marca 03");
		marca.setId(2);
		
		PagamentoDTO pagamento = new PagamentoDTO();
		pagamento.setFormaPagamento(FormaPagamento.CREDITO);
		pagamento.setStatus(StatusPagamento.FINALIZADO);
		pagamento.setCarrinho(2);
		pagamento.setData(LocalDate.now());
		pagamento.setId(3);
		PagamentoDAO pagamentoDAO = new PagamentoDAO();

		ProdutoDTO produto = new ProdutoDTO();
		produto.setNome("Prod 2");
		produto.setEstoque(2);
		produto.setMarca(1);
		produto.setVendedor(1);
		produto.setPreco(2.99);
		produto.setDescricao("Descricao 2");
		produto.setId(3);
		ProdutoDAO produtoDao = new ProdutoDAO();
		System.out.print(produtoDao.get().get(0).getNome());
	}
}
