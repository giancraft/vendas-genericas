package BO;

import java.util.List;
import DTO.MarcaDTO;

public class MarcaBO extends BO{
	public String cadastrarMarca(MarcaDTO marca) {
		return permanencia.marcadao.create(marca)? "Marca cadastrada com sucesso" : "Ocorreu algum problema, tente novamente";
	}
	public List<MarcaDTO> listarMarcas() {
		return permanencia.marcadao.get();
	}
	public List<MarcaDTO> getByNome(String nome) {
		return permanencia.marcadao.getByNome(nome.toLowerCase());
	}
}
