package VIEW;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;

import DTO.ProdutoPagoDTO;

public class Tester {
	public static String pathFile = "./JSON-data/produtopago.json";
	
	public static void main(String[] args) {try {
		ProdutoPagoDTO produto = new ProdutoPagoDTO();
		produto.setIdPagamento(2);
		produto.setNome("nome");
		produto.setPreco(2.4);
		produto.setQuantidade(4);
        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
    	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
    	produto.setId(jsonArray.length());
        jsonArray.put(produto.toArray());
        try (FileWriter file = new FileWriter(pathFile)) {
            file.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
	}
}
