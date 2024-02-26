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
	public List<ProdutoDTO> find(int id) {
		ProdutoDTO prod = new ProdutoDTO();
		prod.setId(id);
		return permanencia.produtodao.find(prod);
	}
	public List<ProdutoDTO> obterPorId(CarrinhoProdutoDTO carrinho) {
		ProdutoDTO prod = new ProdutoDTO();
		prod.setId(carrinho.getIdProduto());
		return permanencia.produtodao.find(prod);
	}
	public List<ProdutoPagoDTO> pagoObterPorId(int id) {
		return permanencia.produtopagoDao.getByPagamento(id);
	}
	
	public List<ProdutoDTO> listarProdutosCliente(MarcaDTO marca){
		return marca.isEmpty()? permanencia.produtodao.get() : permanencia.produtodao.getByMarcaCliente(marca)  ;
	}
	public List<ProdutoDTO> listarProdutos(MarcaDTO marca){
		return marca.isEmpty()? permanencia.produtodao.getByVendedor(VendedorView.vendedorLogado.getId()) : permanencia.produtodao.getByMarca(marca, VendedorView.vendedorLogado.getId())  ;
	}
	public String alterarDados(ProdutoDTO prod) {
		return permanencia.produtodao.update(prod)? "Produto atualizado" : "Ocorreu um erro inesperado";
	}
	public void mostrarDescricao(int idProduto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setId(idProduto);
		ProdutoDTO produto = permanencia.produtodao.find(produtoDTO).get(0);
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
	public String excluirProduto(ProdutoDTO produto) {
		return permanencia.produtodao.delete(produto)? "Produto deletado com sucesso" :"Ocorreu um erro inesperado";
	}
	public String cadastrarProduto (ProdutoDTO produto) {
		return permanencia.produtodao.create(produto)? "Produto cadastrado com sucesso": "Ocorreu um erro, tente novamente";
	}
	public int qtdDigitos (int n) {
		n = Math.abs(n);
		if (n == 0) return 1;
		else return (int) (Math.log10 (n) + 1); 
	}
}
