package BO;

import java.util.List;
import DTO.MarcaDTO;

public class MarcaBO extends BO{
	public static String cadastrarMarca(MarcaDTO marca) {
		return marcadao.create(marca)? "Marca cadastrada com sucesso" : "Ocorreu algum problema, tente novamente";
	}
	public static List<MarcaDTO> listarMarcas() {
		return marcadao.get();
	}
	public static List<MarcaDTO> getByNome(String nome) {
		return marcadao.getByNome(nome.toLowerCase());
	}
}
