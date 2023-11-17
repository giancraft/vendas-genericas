package VIEW;

import DAO.ClienteDAO;
import DTO.ClienteDTO;

public class mainView {

	public static void main(String[] args) {
		ClienteDTO clientD = new ClienteDTO();
		clientD.setNome("teste");
		clientD.setEmail("teste@");
		clientD.setTelefone("999999999");
		clientD.setSenha("999999999");
		ClienteDAO clientDa = new ClienteDAO();
		clientDa.create(clientD);
		
	}

}
