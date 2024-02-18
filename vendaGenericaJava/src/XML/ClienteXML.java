package XML;

import java.sql.ResultSet;
import java.util.List;

import DTO.ContaDTO;
import Interfaces.ClienteInterface;

public class ClienteXML implements ClienteInterface{

	@Override
	public boolean create(ContaDTO cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ContaDTO cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ContaDTO> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContaDTO> find(ContaDTO cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContaDTO> findByEmail(ContaDTO cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContaDTO> findByEmailPassword(ContaDTO cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContaDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(ContaDTO cliente) {
		// TODO Auto-generated method stub
		return false;
	}

}
