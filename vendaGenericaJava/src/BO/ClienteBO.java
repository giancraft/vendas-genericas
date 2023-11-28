package BO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAO.ClienteDAO;
import DTO.ContaDTO;
import VIEW.ClienteView;

public class ClienteBO {

    public static boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean validarTelefone(String telefone) {
        // Aceita apenas números e exatamente 9 dígitos
        String regex = "^[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telefone);
        return matcher.matches();
    }

    public static String calcularMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] hash = md.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void alterarDados(ContaDTO cliente) {
    	ClienteDAO clientedao = new ClienteDAO();
    	List<ContaDTO> email = clientedao.findByEmail(cliente);
    	if(!(email.size()>0)) {
    		clientedao.create(cliente);
    		System.out.println("Cliente cadastrado com sucesso");
    	}else {
    		System.out.println("Este e-mail já foi cadastrado como cliente");
    	}
    }
    public static void cadastrarCliente(ContaDTO cliente) {
    	ClienteDAO clientedao = new ClienteDAO();
    	clientedao.update(cliente);
    }
    public static void logarCliente(ContaDTO cliente) {
    	ClienteDAO clientedao = new ClienteDAO();
    	List<ContaDTO> email = clientedao.findByEmail(cliente);
    	if((email.size()>0)) {
        	List<ContaDTO> pass = clientedao.findByEmailPassword(cliente);
        	if((pass.size()>0)) {
        		System.out.println("Cliente logado com sucesso");
        		ClienteView.clienteLogado = pass.get(0);
        		ClienteView.loginStatus = true;
        	}else {
        		System.out.println("E-mail e senhas não coicidem");
        	}
    	}else {
    		System.out.println("Este e-mail não foi cadastrado");
    	}
    }
}
