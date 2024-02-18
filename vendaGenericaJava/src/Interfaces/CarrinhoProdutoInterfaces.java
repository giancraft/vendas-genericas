package Interfaces;

import java.sql.ResultSet;
import java.util.List;

import DTO.CarrinhoProdutoDTO;
import DTO.ProdutoDTO;

public interface CarrinhoProdutoInterfaces {
	public boolean create(CarrinhoProdutoDTO carrinhoProduto);
	public boolean update(CarrinhoProdutoDTO carrinhoProduto);
	public boolean delete(CarrinhoProdutoDTO carrinhoProduto);
	public List<CarrinhoProdutoDTO> get() ;
	public List<CarrinhoProdutoDTO> getByCarrinho(int id);
	public List<CarrinhoProdutoDTO> getByProduto(ProdutoDTO produto);
	public List<CarrinhoProdutoDTO> find(CarrinhoProdutoDTO carrinhoProduto);
	public List<CarrinhoProdutoDTO> mountList(ResultSet rs);
}
