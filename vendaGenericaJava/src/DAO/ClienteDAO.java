package DAO;

import DBConnection.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DTO.ClienteDTO;

public class ClienteDAO extends DAO{
	
    protected String table = "cliente";
    
    protected List<String> fillable = new ArrayList<String>(Arrays.asList(
    		"nome",
    		"email",
    		"telefone",
    		"senha"
	));
    
    protected List<String> fillableTimestamps = new ArrayList<String>(Arrays.asList(
    		"created_at",
    		"updated_at"
	));
    
    protected String deleteTimestamps = "deleted_at";
    
    public String getTable() {
		return table;
	}

	public List<String> getFillable() {
		return fillable;
	}

	public boolean create(ClienteDTO cliente) {
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
}
