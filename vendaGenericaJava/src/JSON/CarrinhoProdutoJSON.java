package JSON;
import java.sql.ResultSet;
import java.util.List;

import DTO.CarrinhoProdutoDTO;
import DTO.ProdutoDTO;
import Interfaces.CarrinhoProdutoInterfaces;

public class CarrinhoProdutoJSON implements CarrinhoProdutoInterfaces{

	@Override
	public boolean create(CarrinhoProdutoDTO carrinhoProduto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CarrinhoProdutoDTO carrinhoProduto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CarrinhoProdutoDTO carrinhoProduto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CarrinhoProdutoDTO> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarrinhoProdutoDTO> getByCarrinho(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarrinhoProdutoDTO> getByProduto(ProdutoDTO produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarrinhoProdutoDTO> find(CarrinhoProdutoDTO carrinhoProduto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarrinhoProdutoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
