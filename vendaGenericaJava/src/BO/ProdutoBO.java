package BO;

import java.util.List;

import DAO.MarcaDAO;
import DAO.VendedorDAO;
import DTO.CarrinhoProdutoDTO;
import DTO.ContaDTO;
import DTO.MarcaDTO;
import DTO.ProdutoDTO;
import DTO.ProdutoPagoDTO;
import VIEW.VendedorView;

public class ProdutoBO extends BO {
	public static List<ProdutoDTO> find(int id) {
		ProdutoDTO prod = new ProdutoDTO();
		prod.setId(id);
		return produtodao.find(prod);
	}
	
	public static List<ProdutoDTO> obterPorId(CarrinhoProdutoDTO carrinho) {
		ProdutoDTO prod = new ProdutoDTO();
		prod.setId(carrinho.getIdProduto());
		return produtodao.find(prod);
	}
	public static List<ProdutoPagoDTO> pagoObterPorId(int id) {
		return produtopagoDao.getByPagamento(id);
	}
	
	public static List<ProdutoDTO> listarProdutosCliente(MarcaDTO marca){
		return marca.isEmpty()? produtodao.get() : produtodao.getByMarcaCliente(marca)  ;
	}
	public static List<ProdutoDTO> listarProdutos(MarcaDTO marca){
		return marca.isEmpty()? produtodao.getByVendedor(VendedorView.vendedorLogado.getId()) : produtodao.getByMarca(marca, VendedorView.vendedorLogado.getId())  ;
	}
	public static String alterarDados(ProdutoDTO prod) {
		return produtodao.update(prod)? "Produto atualizado" : "Ocorreu um erro inesperado";
	}
	public static void mostrarDescricao(int idProduto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setId(idProduto);
		ProdutoDTO produto = produtodao.find(produtoDTO).get(0);
		System.out.println("Nome      | "+produto.getNome());
		System.out.println("Estoque   | "+produto.getEstoque());
		System.out.println("Descrição | "+produto.getDescricao());

		MarcaDAO marcaDao = new MarcaDAO();
		MarcaDTO marcaDTO = new MarcaDTO();
		marcaDTO.setId(produto.getMarca());
		marcaDTO = marcaDao.find(marcaDTO).get(0);
		System.out.println("Marca     | "+ marcaDTO.getNome());

		VendedorDAO vendedorDao = new VendedorDAO();
		ContaDTO vendedordto = new ContaDTO();
		vendedordto.setId(produto.getVendedor());
		vendedordto = vendedorDao.find(vendedordto).get(0);
		System.out.println("Vendedor  | "+ vendedordto.getNome());

	}
	public static String excluirProduto(ProdutoDTO produto) {
		return produtodao.delete(produto)? "Produto deletado com sucesso" :"Ocorreu um erro inesperado";
	}
	public static String cadastrarProduto (ProdutoDTO produto) {
		return produtodao.create(produto)? "Produto cadastrado com sucesso": "Ocorreu um erro, tente novamente";
	}
	public static int qtdDigitos (int n) {
		n = Math.abs(n);
		if (n == 0) return 1;
		else return (int) (Math.log10 (n) + 1); 
	}
}
