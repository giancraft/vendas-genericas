package Interfaces;

import java.sql.ResultSet;
import java.util.List;
import DTO.ContaDTO;

public interface VendedorInterface {

	public boolean create(ContaDTO vendedor);
	public boolean update(ContaDTO vendedor);
	public List<ContaDTO> get();
	public List<ContaDTO> find(ContaDTO vendedor);
	public List<ContaDTO> mountList(ResultSet rs);
	public List<ContaDTO> findByEmail(ContaDTO vendedor);
	public List<ContaDTO> findByEmailPassword(ContaDTO vendedor);
	public boolean delete(ContaDTO vendedor);
}
