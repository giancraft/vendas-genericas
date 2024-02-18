package Interfaces;

import java.sql.ResultSet;
import java.util.List;
import DTO.ProdutoDTO;
import DTO.ProdutoPagoDTO;

public interface ProdutoPagoInterface {

	public boolean create(ProdutoPagoDTO produto);
	public List<ProdutoPagoDTO> getByCarrinho(int id);
	public boolean update(ProdutoPagoDTO produto);
	public boolean delete(ProdutoPagoDTO produto);
	public List<ProdutoPagoDTO> get();
	public List<ProdutoPagoDTO> find(ProdutoDTO produto);
	public List<ProdutoPagoDTO> getByPagamento(int id);
	public List<ProdutoPagoDTO> mountList(ResultSet rs);
}
