package Interfaces;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import DTO.PagamentoDTO;

public interface PagamentoInterface {

	public boolean create(PagamentoDTO pagamento);
	public boolean update(PagamentoDTO pagamento);
	public boolean delete(PagamentoDTO pagamento);
	public List<PagamentoDTO> get();
	public List<PagamentoDTO> getLastByCarrinho(int id);
	public List<PagamentoDTO> getByData(LocalDate inicio, LocalDate fim);
	public List<PagamentoDTO> find(PagamentoDTO pagamento);
	public List<PagamentoDTO> getByCarrinho(int id);
	public List<PagamentoDTO> mountList(ResultSet rs);
}
