package JSON;
import java.sql.ResultSet;
import java.util.List;

import DTO.MarcaDTO;
import Interfaces.MarcaInterface;

public class MarcaJSON implements MarcaInterface {

	@Override
	public boolean create(MarcaDTO marca) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(MarcaDTO marca) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MarcaDTO> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MarcaDTO> find(MarcaDTO marca) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MarcaDTO> getByNome(String nomeM) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MarcaDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(MarcaDTO marca) {
		// TODO Auto-generated method stub
		return false;
	}

}
