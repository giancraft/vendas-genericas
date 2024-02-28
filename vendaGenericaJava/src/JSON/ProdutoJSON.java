package JSON;
import java.sql.ResultSet;
import java.util.List;

import DTO.MarcaDTO;
import DTO.ProdutoDTO;
import Interfaces.ProdutoInterface;
public class ProdutoJSON extends JsonArchive implements ProdutoInterface{

	@Override
	public boolean create(ProdutoDTO produto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ProdutoDTO produto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(ProdutoDTO produto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProdutoDTO> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoDTO> getByVendedor(int idVendedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoDTO> find(ProdutoDTO produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoDTO> getByMarca(MarcaDTO marca, int idVendedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoDTO> getByMarcaCliente(MarcaDTO marca) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
