package JSON;
import java.sql.ResultSet;
import java.util.List;

import DTO.ProdutoDTO;
import DTO.ProdutoPagoDTO;
import Interfaces.ProdutoPagoInterface;
public class ProdutoPagoJSON extends JsonArchive implements ProdutoPagoInterface{

	@Override
	public boolean create(ProdutoPagoDTO produto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProdutoPagoDTO> getByCarrinho(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ProdutoPagoDTO produto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(ProdutoPagoDTO produto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProdutoPagoDTO> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoPagoDTO> find(ProdutoDTO produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoPagoDTO> getByPagamento(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoPagoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
