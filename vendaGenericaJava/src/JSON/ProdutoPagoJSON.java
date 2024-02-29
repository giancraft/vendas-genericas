package JSON;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import DTO.ProdutoDTO;
import DTO.ProdutoPagoDTO;
import Interfaces.ProdutoPagoInterface;
public class ProdutoPagoJSON extends JsonArchive implements ProdutoPagoInterface{
	public String pathFile = "./JSON-data/produtopago.json";
	@Override
	public boolean create(ProdutoPagoDTO produto) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
        	produto.setId(jsonArray.length());
            jsonArray.put(produto.toArray());
            try (FileWriter file = new FileWriter(pathFile)) {
                file.write(jsonArray.toString());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public List<ProdutoPagoDTO> getByCarrinho(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ProdutoPagoDTO produto) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
        	jsonArray.put(produto.getId(), produto.toArray());
            try (FileWriter file = new FileWriter(pathFile)) {
                file.write(jsonArray.toString());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean delete(ProdutoPagoDTO produto) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
            if(!content.equals("")) {
            	JSONArray jsonArray = new JSONArray(content);
                jsonArray.remove(produto.getId());
                try (FileWriter file = new FileWriter(pathFile)) {
                    file.write(jsonArray.toString());
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
            	return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public List<ProdutoPagoDTO> get() {
		List<ProdutoPagoDTO> produtos = new ArrayList<ProdutoPagoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	ProdutoPagoDTO produto = new ProdutoPagoDTO();
	            	produto.setId(i);
	            	produto.setIdPagamento((int) innerArray.get(1));
	            	produto.setNome((String) innerArray.get(2));
	            	produto.setPreco((Double) Double.parseDouble(String.valueOf(innerArray.get(3))));
	            	produto.setQuantidade((int) innerArray.get(4));	
	        		produtos.add(produto);
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return produtos;
	}

	@Override
	public List<ProdutoPagoDTO> find(ProdutoDTO produto) {
		List<ProdutoPagoDTO> produtos = new ArrayList<ProdutoPagoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
            	JSONArray innerArray = jsonArray.getJSONArray(produto.getId());
            	ProdutoPagoDTO produtopag = new ProdutoPagoDTO();
            	produtopag.setId((int) innerArray.get(0));
            	produtopag.setIdPagamento((int) innerArray.get(1));
            	produtopag.setNome((String) innerArray.get(2));
            	produtopag.setPreco((Double) Double.parseDouble(String.valueOf(innerArray.get(3))));
            	produtopag.setQuantidade((int) innerArray.get(4));	
        		produtos.add(produtopag);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return produtos;
	}

	@Override
	public List<ProdutoPagoDTO> getByPagamento(int id) {
		List<ProdutoPagoDTO> produtos = new ArrayList<ProdutoPagoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	ProdutoPagoDTO produto = new ProdutoPagoDTO();
	            	produto.setId(i);
	            	produto.setIdPagamento((int) innerArray.get(1));
	            	produto.setNome((String) innerArray.get(2));
	            	produto.setPreco((Double) Double.parseDouble(String.valueOf(innerArray.get(3))));
	            	produto.setQuantidade((int) innerArray.get(4));	
	            	if(produto.getIdPagamento()==id) {
		        		produtos.add(produto);
	            	}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return produtos;
	}

	@Override
	public List<ProdutoPagoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
