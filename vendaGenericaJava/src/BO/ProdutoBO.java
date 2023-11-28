package BO;

import java.util.List;

import DAO.MarcaDAO;
import DAO.ProdutoDAO;
import DAO.VendedorDAO;
import DTO.ContaDTO;
import DTO.MarcaDTO;
import DTO.ProdutoDTO;

public class ProdutoBO {
	public static List<ProdutoDTO> listarProdutos(){
		ProdutoDAO produtodao = new ProdutoDAO();
		return produtodao.get();
	}
	public static void mostrarDescricao(int idProduto) {
		ProdutoDAO produtodao = new ProdutoDAO();
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
	public static int qtdDigitos (int n) {
		n = Math.abs(n);
		if (n == 0) return 1;
		else return (int) (Math.log10 (n) + 1); 
	}
}
