package JSON;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import DTO.CarrinhoDTO;
import DTO.CarrinhoProdutoDTO;
import Interfaces.CarrinhoInterface;

public class CarrinhoJSON extends JsonArchive implements CarrinhoInterface{
	public String pathFile = "./JSON-data/carrinho.json";
	@Override
	public boolean create(CarrinhoDTO carrinho) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
        	carrinho.setId(jsonArray.length());
            jsonArray.put(carrinho.toArray());
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
	public boolean update(CarrinhoDTO carrinho) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
        	jsonArray.put(carrinho.getId(), carrinho.toArray());
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
	public List<CarrinhoDTO> get() {
		List<CarrinhoDTO> carrinhos = new ArrayList<CarrinhoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	        		CarrinhoDTO carrinho = new CarrinhoDTO();
	        		carrinho.setCliente((int) innerArray.get(0));
	        		carrinho.setNome((String) innerArray.get(1));
	        		carrinho.setId((int) innerArray.get(2));
	        		carrinhos.add(carrinho);
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return carrinhos;
	}

	@Override
	public List<CarrinhoDTO> find(CarrinhoDTO carrinho) {
		List<CarrinhoDTO> carrinhos = new ArrayList<CarrinhoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
            	JSONArray innerArray = jsonArray.getJSONArray(carrinho.getId());
        		CarrinhoDTO carrinhodto = new CarrinhoDTO();
        		carrinhodto.setCliente((int) innerArray.get(0));
        		carrinhodto.setNome((String) innerArray.get(1));
        		carrinhodto.setId((int) innerArray.get(2));
        		carrinhos.add(carrinhodto);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return carrinhos;
	}

	@Override
	public List<CarrinhoDTO> getByCliente(int idCliente) {
		List<CarrinhoDTO> carrinhos = new ArrayList<CarrinhoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	        		CarrinhoDTO carrinhodto = new CarrinhoDTO();
	        		carrinhodto.setCliente((int) innerArray.get(0));
	        		carrinhodto.setNome((String) innerArray.get(1));
	        		carrinhodto.setId((int) innerArray.get(2));
	        		if(carrinhodto.getCliente()==idCliente) {
		        		carrinhos.add(carrinhodto);
	        		}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return carrinhos;
	}

	@Override
	public List<CarrinhoDTO> getByName(String nome) {
		List<CarrinhoDTO> carrinhos = new ArrayList<CarrinhoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	        		CarrinhoDTO carrinhodto = new CarrinhoDTO();
	        		carrinhodto.setCliente((int) innerArray.get(0));
	        		carrinhodto.setNome((String) innerArray.get(1));
	        		carrinhodto.setId((int) innerArray.get(2));
	        		if(carrinhodto.getNome()==nome) {
		        		carrinhos.add(carrinhodto);
	        		}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return carrinhos;
	}

	@Override
	public List<CarrinhoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(CarrinhoDTO carrinho) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
            if(!content.equals("")) {
            	JSONArray jsonArray = new JSONArray(content);
                jsonArray.remove(carrinho.getId());
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
	public boolean insertProdutoInCarrinho(int produtoId, int quantidade, int carrinhoId) {
		String alterPathFile = "/JSON-data/carrinho-produto.json";
		CarrinhoProdutoDTO carrinhoProduto = new CarrinhoProdutoDTO();
		carrinhoProduto.setIdCarrinho(carrinhoId);
		carrinhoProduto.setIdProduto(produtoId);
		carrinhoProduto.setQuantidade(quantidade);
        try {
            String content = new String(Files.readAllBytes(Paths.get(alterPathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
            jsonArray.put(carrinhoProduto.toArray());
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

}
