package JSON;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import DTO.CarrinhoProdutoDTO;
import DTO.ProdutoDTO;
import Interfaces.CarrinhoProdutoInterfaces;

public class CarrinhoProdutoJSON extends JsonArchive implements CarrinhoProdutoInterfaces{

	public String pathFile = "./JSON-data/carrinho-produto.json";
	@Override
	public boolean create(CarrinhoProdutoDTO carrinhoProduto) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
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

	@Override
	public boolean update(CarrinhoProdutoDTO carrinhoProduto) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
        	boolean stop = false;
        	for(int i =0; i<jsonArray.length() || stop;i++) {;
            	JSONArray innerArray = jsonArray.getJSONArray(i);
        		if(carrinhoProduto.getIdCarrinho()==(int) innerArray.get(0) && carrinhoProduto.getIdProduto() == (int) innerArray.get(1)) {
                	jsonArray.put(i, carrinhoProduto.toArray());
                	stop=true;
        		}
        	}
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
	public boolean delete(CarrinhoProdutoDTO carrinhoProduto) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
        	boolean stop = false;
        	for(int i =0; i<jsonArray.length() || stop;i++) {;
            	JSONArray innerArray = jsonArray.getJSONArray(i);
        		if(carrinhoProduto.getIdCarrinho()==(int) innerArray.get(0) && carrinhoProduto.getIdProduto() == (int) innerArray.get(1)) {
                	jsonArray.remove(i);
                	stop=true;
        		}
        	}
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
	public List<CarrinhoProdutoDTO> get() {
		List<CarrinhoProdutoDTO> carrinhoProdutos = new ArrayList<CarrinhoProdutoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	CarrinhoProdutoDTO carrinhoProd = new CarrinhoProdutoDTO();
	        		carrinhoProd.setIdCarrinho((int) innerArray.get(0));
	        		carrinhoProd.setIdProduto((int) innerArray.get(1));
	        		carrinhoProd.setQuantidade((int) innerArray.get(2));
	        		carrinhoProdutos.add(carrinhoProd);
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return carrinhoProdutos;
	}

	@Override
	public List<CarrinhoProdutoDTO> getByCarrinho(int id) {
		List<CarrinhoProdutoDTO> carrinhoProdutos = new ArrayList<CarrinhoProdutoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	CarrinhoProdutoDTO carrinhoProd = new CarrinhoProdutoDTO();
	        		carrinhoProd.setIdCarrinho((int) innerArray.get(0));
	        		carrinhoProd.setIdProduto((int) innerArray.get(1));
	        		carrinhoProd.setQuantidade((int) innerArray.get(2));
	        		if(carrinhoProd.getIdCarrinho()==id) {
		        		carrinhoProdutos.add(carrinhoProd);
	        		}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return carrinhoProdutos;
	}

	@Override
	public List<CarrinhoProdutoDTO> getByProduto(ProdutoDTO produto) {
		List<CarrinhoProdutoDTO> carrinhoProdutos = new ArrayList<CarrinhoProdutoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	CarrinhoProdutoDTO carrinhoProd = new CarrinhoProdutoDTO();
	        		carrinhoProd.setIdCarrinho((int) innerArray.get(0));
	        		carrinhoProd.setIdProduto((int) innerArray.get(1));
	        		carrinhoProd.setQuantidade((int) innerArray.get(2));
	        		if(carrinhoProd.getIdProduto()==produto.getId()) {
		        		carrinhoProdutos.add(carrinhoProd);
	        		}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return carrinhoProdutos;
	}

	@Override
	public List<CarrinhoProdutoDTO> find(CarrinhoProdutoDTO carrinhoProduto) {
		List<CarrinhoProdutoDTO> carrinhoProdutos = new ArrayList<CarrinhoProdutoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	CarrinhoProdutoDTO carrinhoProd = new CarrinhoProdutoDTO();
	        		carrinhoProd.setIdCarrinho((int) innerArray.get(0));
	        		carrinhoProd.setIdProduto((int) innerArray.get(1));
	        		carrinhoProd.setQuantidade((int) innerArray.get(2));
	        		if(carrinhoProd.getIdProduto()==carrinhoProduto.getIdProduto() && carrinhoProd.getIdCarrinho() == carrinhoProduto.getIdCarrinho()) {
		        		carrinhoProdutos.add(carrinhoProd);
	        		}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return carrinhoProdutos;
	}

	@Override
	public List<CarrinhoProdutoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
