package Interfaces;

import java.sql.ResultSet;
import java.util.List;
import DTO.MarcaDTO;
import DTO.ProdutoDTO;

public interface ProdutoInterface {
	public boolean create(ProdutoDTO produto);
	public boolean update(ProdutoDTO produto);
	public boolean delete(ProdutoDTO produto);
	public List<ProdutoDTO> get();
	public List<ProdutoDTO> getByVendedor(int idVendedor);
	public List<ProdutoDTO> find(ProdutoDTO produto);
	public List<ProdutoDTO> getByMarca(MarcaDTO marca,int idVendedor);
	public List<ProdutoDTO> getByMarcaCliente(MarcaDTO marca);
	public List<ProdutoDTO> mountList(ResultSet rs);
}
