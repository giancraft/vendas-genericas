package JSON;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import DTO.MarcaDTO;
import DTO.ProdutoDTO;
import Interfaces.ProdutoInterface;
public class ProdutoJSON extends JsonArchive implements ProdutoInterface{
	public String pathFile = "./JSON-data/produto.json";
	@Override
	public boolean create(ProdutoDTO produto) {
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
	public boolean update(ProdutoDTO produto) {
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
	public boolean delete(ProdutoDTO produto) {
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
	public List<ProdutoDTO> get() {
		List<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	ProdutoDTO produto = new ProdutoDTO();
	            	produto.setDescricao((String) innerArray.get(4));
	            	produto.setEstoque((int) innerArray.get(2));
	            	produto.setId((int) innerArray.get(3));
	            	produto.setMarca((int) innerArray.get(5));
	            	produto.setNome((String) innerArray.get(0));
	            	produto.setPreco(Double.parseDouble(String.valueOf(innerArray.get(1))));
	            	produto.setVendedor((int) innerArray.get(6));
	        		produtos.add(produto);
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return produtos;
	}

	@Override
	public List<ProdutoDTO> getByVendedor(int idVendedor) {
		List<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	ProdutoDTO produto = new ProdutoDTO();
	            	produto.setDescricao((String) innerArray.get(4));
	            	produto.setEstoque((int) innerArray.get(2));
	            	produto.setId((int) innerArray.get(3));
	            	produto.setMarca((int) innerArray.get(5));
	            	produto.setNome((String) innerArray.get(0));
	            	produto.setPreco(Double.parseDouble(String.valueOf(innerArray.get(1))));
	            	produto.setVendedor((int) innerArray.get(6));
	        		if(produto.getVendedor()==idVendedor) {
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
	public List<ProdutoDTO> find(ProdutoDTO produto) {
		List<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
            	JSONArray innerArray = jsonArray.getJSONArray(produto.getId());
            	ProdutoDTO produtodto = new ProdutoDTO();
            	produtodto.setDescricao((String) innerArray.get(4));
            	produtodto.setEstoque((int) innerArray.get(2));
            	produtodto.setId((int) innerArray.get(3));
            	produtodto.setMarca((int) innerArray.get(5));
            	produtodto.setNome((String) innerArray.get(0));
            	produtodto.setPreco(Double.parseDouble(String.valueOf(innerArray.get(1))));
            	produtodto.setVendedor((int) innerArray.get(6));
    			produtos.add(produtodto);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return produtos;
	}

	@Override
	public List<ProdutoDTO> getByMarca(MarcaDTO marca, int idVendedor) {
		List<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	ProdutoDTO produtodto = new ProdutoDTO();
	            	produtodto.setDescricao((String) innerArray.get(4));
	            	produtodto.setEstoque((int) innerArray.get(2));
	            	produtodto.setId((int) innerArray.get(3));
	            	produtodto.setMarca((int) innerArray.get(5));
	            	produtodto.setNome((String) innerArray.get(0));
	            	produtodto.setPreco(Double.parseDouble(String.valueOf(innerArray.get(1))));
	            	produtodto.setVendedor((int) innerArray.get(6));
	        		if(produtodto.getMarca()==marca.getId() && produtodto.getVendedor()==idVendedor) {
	        			produtos.add(produtodto);
	        		}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return produtos;
	}

	@Override
	public List<ProdutoDTO> getByMarcaCliente(MarcaDTO marca) {
		List<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	ProdutoDTO produtodto = new ProdutoDTO();
	            	produtodto.setDescricao((String) innerArray.get(4));
	            	produtodto.setEstoque((int) innerArray.get(2));
	            	produtodto.setId((int) innerArray.get(3));
	            	produtodto.setMarca((int) innerArray.get(5));
	            	produtodto.setNome((String) innerArray.get(0));
	            	produtodto.setPreco(Double.parseDouble(String.valueOf(innerArray.get(1))));
	            	produtodto.setVendedor((int) innerArray.get(6));
	        		if(produtodto.getMarca()==marca.getId()) {
	        			produtos.add(produtodto);
	        		}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return produtos;
	}

	@Override
	public List<ProdutoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
