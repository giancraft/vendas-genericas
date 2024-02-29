package JSON;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import DTO.ContaDTO;
import Interfaces.VendedorInterface;

public class VendedorJSON extends JsonArchive implements VendedorInterface {
	public String pathFile = "./JSON-data/vendedor.json";
	@Override
	public boolean create(ContaDTO vendedor) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
        	vendedor.setId(jsonArray.length());
            jsonArray.put(vendedor.toArray());
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
	public boolean update(ContaDTO vendedor) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
        	jsonArray.put(vendedor.getId(), vendedor.toArray());
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
	public List<ContaDTO> get() {
		List<ContaDTO> clientes = new ArrayList<ContaDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	        		ContaDTO cliente = new ContaDTO();
	        		cliente.setSenha((String) innerArray.get(0));
	        		cliente.setNome((String) innerArray.get(1));
	        		cliente.setEmail((String) innerArray.get(2));
	        		cliente.setTelefone((String) innerArray.get(3));
	        		cliente.setId((int) innerArray.get(4));
	        		clientes.add(cliente);
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return clientes;
	}

	@Override
	public List<ContaDTO> find(ContaDTO vendedor) {
		List<ContaDTO> clientes = new ArrayList<ContaDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
            	JSONArray innerArray = jsonArray.getJSONArray(vendedor.getId());
        		ContaDTO clientedto = new ContaDTO();
        		clientedto.setSenha((String) innerArray.get(0));
        		clientedto.setNome((String) innerArray.get(1));
        		clientedto.setEmail((String) innerArray.get(2));
        		clientedto.setTelefone((String) innerArray.get(3));
        		clientedto.setId((int) innerArray.get(4));
        		clientes.add(clientedto);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return clientes;
	}

	@Override
	public List<ContaDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContaDTO> findByEmail(ContaDTO vendedor) {
		List<ContaDTO> clientes = new ArrayList<ContaDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	        		ContaDTO clientedto = new ContaDTO();
	        		clientedto.setSenha((String) innerArray.get(0));
	        		clientedto.setNome((String) innerArray.get(1));
	        		clientedto.setEmail((String) innerArray.get(2));
	        		clientedto.setTelefone((String) innerArray.get(3));
	        		clientedto.setId((int) innerArray.get(4));
	        		if(clientedto.getEmail().toString().equals(vendedor.getEmail())) {
		        		clientes.add(vendedor);
	        		}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return clientes;
	}

	@Override
	public List<ContaDTO> findByEmailPassword(ContaDTO vendedor) {
		List<ContaDTO> clientes = new ArrayList<ContaDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	        		ContaDTO clientedto = new ContaDTO();
	        		clientedto.setSenha((String) innerArray.get(0));
	        		clientedto.setNome((String) innerArray.get(1));
	        		clientedto.setEmail((String) innerArray.get(2));
	        		clientedto.setTelefone((String) innerArray.get(3));
	        		clientedto.setId((int) innerArray.get(4));
	        		if(clientedto.getEmail().toString().equals(vendedor.getEmail())&& clientedto.getSenha().toString().equals(vendedor.getSenha())) {
		        		clientes.add(vendedor);
	        		}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return clientes;
	}

	@Override
	public boolean delete(ContaDTO vendedor) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
            if(!content.equals("")) {
            	JSONArray jsonArray = new JSONArray(content);
                jsonArray.remove(vendedor.getId());
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

}
