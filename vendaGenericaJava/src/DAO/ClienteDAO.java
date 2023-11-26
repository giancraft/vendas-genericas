package DAO;

import DBConnection.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DTO.ContaDTO;

public class ClienteDAO extends DAO{
	protected String primaryKey = "idCliente";
	public String table = "cliente";
    
    public List<String> fillable = new ArrayList<String>(Arrays.asList(
    		"nome",
    		"email",
    		"telefone",
    		"senha"
	));
    
    public String getTable() {
		return table;
	}

	public List<String> getFillable() {
		return fillable;
	}

	public boolean create(ContaDTO cliente) {
    	try {
    		Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            
            strBuilder.append("INSERT INTO ");
            strBuilder.append(getTable());
            strBuilder.append(fillable.toString().replace('[', '(').replace(']', ')'));
            strBuilder.append(" VALUES (?, ?, ?, ?)");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            preparedStmt.setString(1, cliente.getNome());
            preparedStmt.setString(2, cliente.getEmail());
            preparedStmt.setString(3, cliente.getTelefone());
            preparedStmt.setString(4, cliente.getSenha());
            
            preparedStmt.executeUpdate();
            preparedStmt.close();
            conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
	public boolean update(ContaDTO cliente) {
		try {
			Connection conn = SQLConnection.connect();
			StringBuilder strBuilder = new StringBuilder();
			
            strBuilder.append("UPDATE ");
            strBuilder.append(getTable());
            strBuilder.append(" SET nome = ?, email = ?, telefone = ?, senha = ? WHERE  ");
            strBuilder.append(this.primaryKey);
            strBuilder.append(" = ");
            strBuilder.append(cliente.getId());
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
           
            preparedStmt.setString(1, cliente.getNome());
            preparedStmt.setString(2, cliente.getEmail());
            preparedStmt.setString(3, cliente.getTelefone());
            preparedStmt.setString(4, cliente.getSenha());
            
            int affectedRows = preparedStmt.executeUpdate();
            
            preparedStmt.close();
            conn.close();
            
            return affectedRows > 0;
		} catch (Exception e) {
	       	 e.printStackTrace();
	         return false;
	    }
	}
	public List<ContaDTO> get() {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            ResultSet rs = preparedStmt.executeQuery();
            List<ContaDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<ContaDTO> find(ContaDTO cliente) {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            strBuilder.append("	WHERE ");
            strBuilder.append(this.primaryKey);
            strBuilder.append("= ?");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            
            preparedStmt.setInt(1, cliente.getId());
            ResultSet rs = preparedStmt.executeQuery();
            List<ContaDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<ContaDTO> mountList(ResultSet rs) {
		List<ContaDTO> listObj = new ArrayList<ContaDTO>();
        try {
            while (rs.next()) {
            	ContaDTO obj = new ContaDTO();
            	
                obj.setId(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setEmail(rs.getString(3));
                obj.setTelefone(rs.getString(4));
                obj.setSenha(rs.getString(5));
                
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	public boolean delete(ContaDTO cliente) {
		try {
			Connection conn = SQLConnection.connect();
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("DELETE FROM ");
			strBuilder.append(getTable());
			
            strBuilder.append(" WHERE ");
            strBuilder.append(this.primaryKey);
            strBuilder.append("= ");
            strBuilder.append(cliente.getId());
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            
            
            preparedStmt.executeUpdate();
            preparedStmt.close();
            conn.close();
            return true;
		} catch (Exception e) {
	       	 e.printStackTrace();
	         return false;
	    }
	}
}
