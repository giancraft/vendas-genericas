package VIEW;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;

import DTO.CarrinhoProdutoDTO;

public class Tester {
	protected static String pathFile = "JSON-data/carrinho-produto.json";
	
	public static void main(String[] args) {
		CarrinhoProdutoDTO carrinhoProduto = new CarrinhoProdutoDTO();
		carrinhoProduto.setQuantidade(1);
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
            jsonArray.put(carrinhoProduto.toArray());
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
