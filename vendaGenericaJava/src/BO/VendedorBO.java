package BO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import DTO.ContaDTO;
import VIEW.VendedorView;

public class VendedorBO extends BO {

    public static boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean validarTelefone(String telefone) {
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
    public static String cadastrarVendedor(ContaDTO vendedor) {
    	List<ContaDTO> email = vendedordao.findByEmail(vendedor);
    	if(!(email.size()>0)) {
    		vendedordao.create(vendedor);
    		VendedorView.redirectTo="";
    		return "Vendedor cadastrado com sucesso";
    	}else {
    		VendedorView.redirectTo="cadastro";
    		return "Este e-mail já foi cadastrado como cliente";
    	}
    }
    public static String alterarDados(ContaDTO vendedor) {
    	if(vendedordao.update(vendedor)){
        	return "Dados alterados com sucesso";
    	}else {
        	return "Dados não puderam ser alterados, entre em contato com o suporte";
    	}
    }
    public static String logarVendedor(ContaDTO vendedor) {
    	List<ContaDTO> email = vendedordao.findByEmail(vendedor);
    	if((email.size()>0)) {
        	List<ContaDTO> pass = vendedordao.findByEmailPassword(vendedor);
        	if((pass.size()>0)) {
        		VendedorView.vendedorLogado = pass.get(0);
        		VendedorView.loginStatus = true;
        		VendedorView.redirectTo="";
        		return "Vendedor logado com sucesso";
        	}else {
        		VendedorView.redirectTo="login";
        		return "E-mail e senhas não coicidem";
        	}
    	}else {
    		VendedorView.redirectTo="login";
    		return "Este e-mail não foi cadastrado";
    	}
    }
}
