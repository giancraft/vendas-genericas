package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DBConnection.SQLConnection;
import DTO.ContaDTO;
import Interfaces.VendedorInterface;

public class VendedorDAO extends DAO implements VendedorInterface{
	protected String primaryKey = "idVendedor";
	public String table = "vendedor";
    
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

	public boolean create(ContaDTO vendedor) {
    	try {
    		Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            
            strBuilder.append("INSERT INTO ");
            strBuilder.append(getTable());
            strBuilder.append(fillable.toString().replace('[', '(').replace(']', ')'));
            strBuilder.append(" VALUES (?, ?, ?, ?)");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            preparedStmt.setString(1, vendedor.getNome());
            preparedStmt.setString(2, vendedor.getEmail());
            preparedStmt.setString(3, vendedor.getTelefone());
            preparedStmt.setString(4, vendedor.getSenha());
            
            preparedStmt.executeUpdate();
            preparedStmt.close();
            conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
	public boolean update(ContaDTO vendedor) {
		try {
			Connection conn = SQLConnection.connect();
			StringBuilder strBuilder = new StringBuilder();
			
            strBuilder.append("UPDATE ");
            strBuilder.append(getTable());
            strBuilder.append(" SET nome = ?, email = ?, telefone = ?, senha = ? WHERE ");
            strBuilder.append(this.primaryKey);
            strBuilder.append("= ? ");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            preparedStmt.setString(1, vendedor.getNome());
            preparedStmt.setString(2, vendedor.getEmail());
            preparedStmt.setString(3, vendedor.getTelefone());
            preparedStmt.setString(4, vendedor.getSenha());
            preparedStmt.setInt(5, vendedor.getId());
            
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
	public List<ContaDTO> find(ContaDTO vendedor) {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            strBuilder.append("	WHERE ");
            strBuilder.append(this.primaryKey);
            strBuilder.append("= ? ");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            
            preparedStmt.setInt(1, vendedor.getId());
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
	public List<ContaDTO> findByEmail(ContaDTO vendedor) {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            strBuilder.append("	WHERE email = ?");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            
            preparedStmt.setString(1, vendedor.getEmail());
            ResultSet rs = preparedStmt.executeQuery();
            List<ContaDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<ContaDTO> findByEmailPassword(ContaDTO vendedor) {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            strBuilder.append("	WHERE email = ? AND senha = ?");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            
            preparedStmt.setString(1, vendedor.getEmail());
            preparedStmt.setString(2, vendedor.getSenha());
            ResultSet rs = preparedStmt.executeQuery();
            List<ContaDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public boolean delete(ContaDTO vendedor) {
		try {
			Connection conn = SQLConnection.connect();
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("DELETE FROM ");
			strBuilder.append(getTable());
			
            strBuilder.append(" WHERE ");
            strBuilder.append(this.primaryKey);
            strBuilder.append("= ? ");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());

            preparedStmt.setInt(2, vendedor.getId());
            
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
