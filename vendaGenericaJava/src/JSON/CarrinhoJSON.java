package JSON;

import java.sql.ResultSet;
import java.util.List;

import DTO.CarrinhoDTO;
import Interfaces.CarrinhoInterface;

import org.json.JSONException;
import org.json.JSONObject;


public class CarrinhoJSON implements CarrinhoInterface{

	@Override
	public boolean create(CarrinhoDTO carrinho) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CarrinhoDTO carrinho) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CarrinhoDTO> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarrinhoDTO> find(CarrinhoDTO carrinho) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarrinhoDTO> getByCliente(int idCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarrinhoDTO> getByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarrinhoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(CarrinhoDTO carrinho) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertProdutoInCarrinho(int produtoId, int quantidade, int carrinhoId) {
		// TODO Auto-generated method stub
		return false;
	}

}
