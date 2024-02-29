package JSON;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.ContaDTO;
import Interfaces.ClienteInterface;

import org.json.JSONArray;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClienteJSON extends JsonArchive implements ClienteInterface{
	public String pathFile = "./JSON-data/vendedor.json";
	@Override
	public boolean create(ContaDTO cliente) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
    		cliente.setId(jsonArray.length());
            jsonArray.put(cliente.toArray());
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
	public boolean update(ContaDTO cliente) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
        	jsonArray.put(cliente.getId(), cliente.toArray());
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
	public List<ContaDTO> find(ContaDTO cliente) {
		List<ContaDTO> clientes = new ArrayList<ContaDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
            	JSONArray innerArray = jsonArray.getJSONArray(cliente.getId());
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
	public List<ContaDTO> findByEmail(ContaDTO cliente) {
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
	        		if(clientedto.getEmail()==cliente.getEmail()) {
		        		clientes.add(cliente);
	        		}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return clientes;
	}

	@Override
	public List<ContaDTO> findByEmailPassword(ContaDTO cliente) {
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
	        		if(clientedto.getEmail()==cliente.getEmail() && clientedto.getSenha()==cliente.getSenha()) {
		        		clientes.add(cliente);
	        		}
	        	}
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
	public boolean delete(ContaDTO cliente) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
            if(!content.equals("")) {
            	JSONArray jsonArray = new JSONArray(content);
                jsonArray.remove(cliente.getId());
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
