package Interfaces;

import java.sql.ResultSet;
import java.util.List;

import DTO.CarrinhoDTO;

public interface CarrinhoInterface {

	public boolean create(CarrinhoDTO carrinho);
	public boolean update(CarrinhoDTO carrinho);
	public List<CarrinhoDTO> get();
	public List<CarrinhoDTO> find(CarrinhoDTO carrinho);
	public List<CarrinhoDTO> getByCliente(int idCliente);
	public List<CarrinhoDTO> getByName(String nome);
	public List<CarrinhoDTO> mountList(ResultSet rs);
	public boolean delete(CarrinhoDTO carrinho) ;
	public boolean insertProdutoInCarrinho(int produtoId, int quantidade, int carrinhoId);
}
