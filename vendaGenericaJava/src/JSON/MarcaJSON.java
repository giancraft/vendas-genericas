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
import Interfaces.MarcaInterface;

public class MarcaJSON extends JsonArchive implements MarcaInterface {
	public String pathFile = "./JSON-data/marca.json";
	@Override
	public boolean create(MarcaDTO marca) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
        	marca.setId(jsonArray.length());
            jsonArray.put(marca.toArray());
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
	public boolean update(MarcaDTO marca) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
        	jsonArray.put(marca.getId(), marca.toArray());
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
	public List<MarcaDTO> get() {
		List<MarcaDTO> marcas = new ArrayList<MarcaDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	MarcaDTO marca = new MarcaDTO();
	        		marca.setNome((String) innerArray.get(0));
	        		marca.setId(i);
	        		marcas.add(marca);
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return marcas;
	}

	@Override
	public List<MarcaDTO> find(MarcaDTO marca) {
		List<MarcaDTO> marcas = new ArrayList<MarcaDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
            	JSONArray innerArray = jsonArray.getJSONArray(marca.getId());
            	MarcaDTO marcadto = new MarcaDTO();
            	marcadto.setNome((String) innerArray.get(0));
        		marcadto.setId(marca.getId());
    			marcas.add(marcadto);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return marcas;
	}

	@Override
	public List<MarcaDTO> getByNome(String nomeM) {
		List<MarcaDTO> marcas = new ArrayList<MarcaDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	MarcaDTO marcadto = new MarcaDTO();
	            	marcadto.setNome((String) innerArray.get(0));
	        		marcadto.setId(i);
	        		if(marcadto.getNome().toString().equals(nomeM)) {
	        			marcas.add(marcadto);
	        		}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return marcas;
	}

	@Override
	public List<MarcaDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(MarcaDTO marca) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
            if(!content.equals("")) {
            	JSONArray jsonArray = new JSONArray(content);
                jsonArray.remove(marca.getId());
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
