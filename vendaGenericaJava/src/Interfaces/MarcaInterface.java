package Interfaces;

import java.sql.ResultSet;
import java.util.List;
import DTO.MarcaDTO;

public interface MarcaInterface {

	public boolean create(MarcaDTO marca);
	public boolean update(MarcaDTO marca);
	public List<MarcaDTO> get();
	public List<MarcaDTO> find(MarcaDTO marca);
	public List<MarcaDTO> getByNome(String nomeM);
	public List<MarcaDTO> mountList(ResultSet rs);
	public boolean delete(MarcaDTO marca);
}
