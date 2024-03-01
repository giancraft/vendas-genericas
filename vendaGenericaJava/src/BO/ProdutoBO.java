package BO;

import java.util.List;

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
		return permanencia.getProdutodao().find(prod);
	}
	public List<ProdutoDTO> obterPorId(CarrinhoProdutoDTO carrinho) {
		ProdutoDTO prod = new ProdutoDTO();
		prod.setId(carrinho.getIdProduto());
		return permanencia.getProdutodao().find(prod);
	}
	public List<ProdutoPagoDTO> pagoObterPorId(int id) {
		return permanencia.getProdutopagoDao().getByPagamento(id);
	}
	
	public List<ProdutoDTO> listarProdutosCliente(MarcaDTO marca){
		return marca.isEmpty()? permanencia.getProdutodao().get() : permanencia.getProdutodao().getByMarcaCliente(marca)  ;
	}
	public List<ProdutoDTO> listarProdutos(MarcaDTO marca){
		return marca.isEmpty()? permanencia.getProdutodao().getByVendedor(VendedorView.vendedorLogado.getId()) : permanencia.getProdutodao().getByMarca(marca, VendedorView.vendedorLogado.getId())  ;
	}
	public String alterarDados(ProdutoDTO prod) {
		return permanencia.getProdutodao().update(prod)? "Produto atualizado" : "Ocorreu um erro inesperado";
	}
	public void mostrarDescricao(int idProduto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setId(idProduto);
		ProdutoDTO produto = permanencia.getProdutodao().find(produtoDTO).get(0);
		System.out.println("Nome      | "+produto.getNome());
		System.out.println("Estoque   | "+produto.getEstoque());
		System.out.println("Descrição | "+produto.getDescricao());

		MarcaDTO marcaDTO = new MarcaDTO();
		marcaDTO.setId(produto.getMarca());
		if( permanencia.getMarcadao().find(marcaDTO).size()>0) {
			marcaDTO = permanencia.getMarcadao().find(marcaDTO).get(0);
			System.out.println("Marca     | "+ marcaDTO.getNome());			
		}

		ContaDTO vendedordto = new ContaDTO();
		vendedordto.setId(produto.getVendedor());
		if( permanencia.getVendedordao().find(vendedordto).size()>0) {
			vendedordto = permanencia.getVendedordao().find(vendedordto).get(0);
			System.out.println("Vendedor  | "+ vendedordto.getNome());		
		}

	}
	public String excluirProduto(ProdutoDTO produto) {
		return permanencia.getProdutodao().delete(produto)? "Produto deletado com sucesso" :"Ocorreu um erro inesperado";
	}
	public String cadastrarProduto (ProdutoDTO produto) {
		return permanencia.getProdutodao().create(produto)? "Produto cadastrado com sucesso": "Ocorreu um erro, tente novamente";
	}
	public int qtdDigitos (int n) {
		n = Math.abs(n);
		if (n == 0) return 1;
		else return (int) (Math.log10 (n) + 1); 
	}
}
