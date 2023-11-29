package BO;

import java.util.List;

import DAO.MarcaDAO;
import DTO.MarcaDTO;

public class MarcaBO {
	public static String cadastrarMarca(MarcaDTO marca) {
		MarcaDAO marcadao = new MarcaDAO();
		return marcadao.create(marca)? "Marca cadastrada com sucesso" : "Ocorreu algum problema, tente novamente";
	}
	public static List<MarcaDTO> listarMarcas() {
		MarcaDAO marcadao = new MarcaDAO();
		return marcadao.get();
	}
	public static List<MarcaDTO> getByNome(String nome) {
		MarcaDAO marcadao = new MarcaDAO();
		return marcadao.getByNome(nome.toLowerCase());
	}
}
