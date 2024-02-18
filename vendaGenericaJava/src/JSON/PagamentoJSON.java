package JSON;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import DTO.PagamentoDTO;
import Interfaces.PagamentoInterface;

public class PagamentoJSON implements PagamentoInterface {

	public boolean create(PagamentoDTO pagamento) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(PagamentoDTO pagamento) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(PagamentoDTO pagamento) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<PagamentoDTO> get() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PagamentoDTO> getLastByCarrinho(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PagamentoDTO> getByData(LocalDate inicio, LocalDate fim) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PagamentoDTO> find(PagamentoDTO pagamento) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PagamentoDTO> getByCarrinho(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PagamentoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
