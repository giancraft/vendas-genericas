package JSON;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;

import DTO.PagamentoDTO;
import ENUMS.FormaPagamento;
import ENUMS.StatusPagamento;
import Interfaces.PagamentoInterface;

public class PagamentoJSON extends JsonArchive implements PagamentoInterface {
	public String pathFile = "./JSON-data/pagamento.json";
	public boolean create(PagamentoDTO pagamento) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
        	pagamento.setId(jsonArray.length());
            jsonArray.put(pagamento.toArray());
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

	public boolean update(PagamentoDTO pagamento) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
        	JSONArray jsonArray = new JSONArray(!content.equals("")?content:"[]");
        	jsonArray.put(pagamento.getId(), pagamento.toArray());
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

	public boolean delete(PagamentoDTO pagamento) {
		try {
            String content = new String(Files.readAllBytes(Paths.get(pathFile)));
            if(!content.equals("")) {
            	JSONArray jsonArray = new JSONArray(content);
                jsonArray.remove(pagamento.getId());
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

	public List<PagamentoDTO> get() {
		List<PagamentoDTO> pagamentos = new ArrayList<PagamentoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	PagamentoDTO pagamentodto = new PagamentoDTO();
					String dt = (String) innerArray.get(6);
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate dta = LocalDate.parse(dt, dtf);
	            	pagamentodto.setData(dta);
	            	pagamentodto.setId((int) innerArray.get(8));
	            	pagamentodto.setCarrinho((int) innerArray.get(7));
	            	pagamentodto.setFormaPagamento(FormaPagamento.valueOf((String) innerArray.get(1)));
	            	pagamentodto.setStatus(StatusPagamento.valueOf((String) innerArray.get(2)));
	        		pagamentos.add(pagamentodto);
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return pagamentos;
	}

	public List<PagamentoDTO> getLastByCarrinho(int id) {
		List<PagamentoDTO> pagamentos = new ArrayList<PagamentoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	boolean stop = false;
	        	for(int i =jsonArray.length()-1; i>0;i--) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	PagamentoDTO pagamentodto = new PagamentoDTO();
					String dt = (String) innerArray.get(6);
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate dta = LocalDate.parse(dt, dtf);
	            	pagamentodto.setData(dta);
	            	pagamentodto.setId((int) innerArray.get(8));
	            	pagamentodto.setCarrinho((int) innerArray.get(7));
	            	pagamentodto.setFormaPagamento(FormaPagamento.valueOf((String) innerArray.get(1)));
	            	pagamentodto.setStatus(StatusPagamento.valueOf((String) innerArray.get(2)));
	        		if(pagamentodto.getCarrinho()==id && !stop) {
	        			pagamentos.add(pagamentodto);
	        			stop=true;
	        		}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return pagamentos;
	}

	public List<PagamentoDTO> getByData(LocalDate inicio, LocalDate fim) {
		List<PagamentoDTO> pagamentos = new ArrayList<PagamentoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	PagamentoDTO pagamentodto = new PagamentoDTO();
					String dt = (String) innerArray.get(6);
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate dta = LocalDate.parse(dt, dtf);
	            	pagamentodto.setData(dta);
	            	pagamentodto.setId((int) innerArray.get(8));
	            	pagamentodto.setCarrinho((int) innerArray.get(7));
	            	pagamentodto.setFormaPagamento(FormaPagamento.valueOf((String) innerArray.get(1)));
	            	pagamentodto.setStatus(StatusPagamento.valueOf((String) innerArray.get(2)));
	            	if(dta.isAfter(inicio) && dta.isBefore(fim)) {
		        		pagamentos.add(pagamentodto);
	            	}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return pagamentos;
	}

	public List<PagamentoDTO> find(PagamentoDTO pagamento) {
		List<PagamentoDTO> pagamentos = new ArrayList<PagamentoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	JSONArray innerArray = jsonArray.getJSONArray(pagamento.getId());
	        	PagamentoDTO pagamentodto = new PagamentoDTO();
				String dt = (String) innerArray.get(6);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dta = LocalDate.parse(dt, dtf);
	        	pagamentodto.setData(dta);
	        	pagamentodto.setId((int) innerArray.get(8));
	        	pagamentodto.setCarrinho((int) innerArray.get(7));
	        	pagamentodto.setFormaPagamento(FormaPagamento.valueOf((String) innerArray.get(1)));
	        	pagamentodto.setStatus(StatusPagamento.valueOf((String) innerArray.get(2)));
	    		pagamentos.add(pagamentodto);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return pagamentos;
	}

	public List<PagamentoDTO> getByCarrinho(int id) {
		List<PagamentoDTO> pagamentos = new ArrayList<PagamentoDTO>();
	    try {
	        String content = new String(Files.readAllBytes(Paths.get(pathFile)));
	        if(!content.equals("")) {
	        	JSONArray jsonArray = new JSONArray(content);
	        	for(int i =0; i<jsonArray.length();i++) {;
	            	JSONArray innerArray = jsonArray.getJSONArray(i);
	            	PagamentoDTO pagamentodto = new PagamentoDTO();
					String dt = (String) innerArray.get(6);
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate dta = LocalDate.parse(dt, dtf);
	            	pagamentodto.setData(dta);
	            	pagamentodto.setId((int) innerArray.get(8));
	            	pagamentodto.setCarrinho((int) innerArray.get(7));
	            	pagamentodto.setFormaPagamento(FormaPagamento.valueOf((String) innerArray.get(1)));
	            	pagamentodto.setStatus(StatusPagamento.valueOf((String) innerArray.get(2)));
	        		if(pagamentodto.getCarrinho()==id) {
	        			pagamentos.add(pagamentodto);
	        		}
	        	}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return pagamentos;
	}

	public List<PagamentoDTO> mountList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
