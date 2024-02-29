package VIEW;

import java.util.List;

import BO.ProdutoBO;
import DTO.MarcaDTO;
import DTO.ProdutoDTO;

public class InserirConsultar {

	public static ProdutoBO ProdutoBO= new ProdutoBO();
	
	public static void main(String[] args) {
		ProdutoDTO produto = new ProdutoDTO();
		produto.setDescricao("Descricao teste");
		produto.setEstoque(23);
		produto.setMarca(0);
		produto.setNome("teste");
		produto.setPreco(2.55);
		produto.setVendedor(0);
		ProdutoBO.cadastrarProduto(produto);
		
		List<ProdutoDTO> produtos = ProdutoBO.listarProdutosCliente(new MarcaDTO());
		System.out.print("id  |Nome\n");
		for(int i = 0; i<produtos.size(); i++) {
			System.out.print(i+1);
			for(int x = ProdutoBO.qtdDigitos(i); x<4; x++) {
				System.out.print(" ");
			}
			System.out.print("|"+produtos.get(i).getNome()+"\n");
		}
	}
}
