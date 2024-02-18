package Interfaces;

import java.sql.ResultSet;
import java.util.List;
import DTO.ContaDTO;

public interface ClienteInterface {
	public boolean create(ContaDTO cliente);
	public boolean update(ContaDTO cliente);
	public List<ContaDTO> get();
	public List<ContaDTO> find(ContaDTO cliente);
	public List<ContaDTO> findByEmail(ContaDTO cliente);
	public List<ContaDTO> findByEmailPassword(ContaDTO cliente);
	public List<ContaDTO> mountList(ResultSet rs);
	public boolean delete(ContaDTO cliente);
}
