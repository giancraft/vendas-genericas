package XML;

import java.sql.ResultSet;
import java.util.List;

import DTO.ContaDTO;
import Interfaces.VendedorInterface;

public class VendedorXML implements VendedorInterface {

	@Override
	public boolean create(ContaDTO vendedor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ContaDTO vendedor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ContaDTO> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContaDTO> find(ContaDTO vendedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContaDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContaDTO> findByEmail(ContaDTO vendedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContaDTO> findByEmailPassword(ContaDTO vendedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(ContaDTO vendedor) {
		// TODO Auto-generated method stub
		return false;
	}

}
