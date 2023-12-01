package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DBConnection.SQLConnection;
import DTO.CarrinhoProdutoDTO;
import DTO.ProdutoDTO;

public class CarrinhoProdutoDAO {

	protected String primaryKey = "idCarrinho";
	protected String primaryKeytwo = "idProduto";
	public String table = "carrinhoproduto";
    
    public List<String> fillable = new ArrayList<String>(Arrays.asList(
    		"idCarrinho",
    		"idProduto",
    		"quantidade"
	));
    
    public String getTable() {
		return table;
	}

	public List<String> getFillable() {
		return fillable;
	}

	public boolean create(CarrinhoProdutoDTO carrinhoProduto) {
    	try {
    		Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            
            strBuilder.append("INSERT INTO ");
            strBuilder.append(getTable());
            strBuilder.append(fillable.toString().replace('[', '(').replace(']', ')'));
            strBuilder.append(" VALUES (?, ?, ?)");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            preparedStmt.setInt(1, carrinhoProduto.getIdCarrinho());
            preparedStmt.setInt(2, carrinhoProduto.getIdProduto());
            preparedStmt.setInt(3, carrinhoProduto.getQuantidade());
            
            preparedStmt.executeUpdate();
            preparedStmt.close();
            conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
	public boolean update(CarrinhoProdutoDTO carrinhoProduto) {
		try {
			Connection conn = SQLConnection.connect();
			StringBuilder strBuilder = new StringBuilder();
			
            strBuilder.append("UPDATE ");
            strBuilder.append(getTable());
            strBuilder.append(" SET quantidade = ? WHERE  ");
            strBuilder.append(this.primaryKey);
            strBuilder.append(" = ");
            strBuilder.append(carrinhoProduto.getIdCarrinho());
            strBuilder.append(" AND ");
            strBuilder.append(this.primaryKeytwo);
            strBuilder.append(" = ");
            strBuilder.append(carrinhoProduto.getIdProduto());
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
           
            preparedStmt.setInt(1, carrinhoProduto.getQuantidade());
            
            int affectedRows = preparedStmt.executeUpdate();
            
            preparedStmt.close();
            conn.close();
            
            return affectedRows > 0;
		} catch (Exception e) {
	       	 e.printStackTrace();
	         return false;
	    }
	}

	public boolean delete(CarrinhoProdutoDTO carrinhoProduto) {
		try {
			Connection conn = SQLConnection.connect();
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("DELETE FROM ");
			strBuilder.append(getTable());
			
            strBuilder.append(" WHERE ");
            strBuilder.append(this.primaryKey);
            strBuilder.append(" = ");
            strBuilder.append(carrinhoProduto.getIdCarrinho());
            strBuilder.append(" AND ");
            strBuilder.append(this.primaryKeytwo);
            strBuilder.append(" = ");
            strBuilder.append(carrinhoProduto.getIdProduto());
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
	public List<CarrinhoProdutoDTO> get() {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            ResultSet rs = preparedStmt.executeQuery();
            List<CarrinhoProdutoDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<CarrinhoProdutoDTO> getByCarrinho(int id) {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            strBuilder.append("	WHERE idCarrinho = ?");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());

            preparedStmt.setInt(1, id);
            
            ResultSet rs = preparedStmt.executeQuery();
            List<CarrinhoProdutoDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<CarrinhoProdutoDTO> getByProduto(ProdutoDTO produto) {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            strBuilder.append("	WHERE ");
            strBuilder.append(this.primaryKeytwo);
            strBuilder.append(" = ?");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());

            preparedStmt.setInt(1, produto.getId());
            
            ResultSet rs = preparedStmt.executeQuery();
            List<CarrinhoProdutoDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<CarrinhoProdutoDTO> find(CarrinhoProdutoDTO carrinhoProduto) {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            strBuilder.append("	WHERE ");
            strBuilder.append(this.primaryKey);
            strBuilder.append(" = ");
            strBuilder.append(carrinhoProduto.getIdCarrinho());
            strBuilder.append(" AND ");
            strBuilder.append(this.primaryKeytwo);
            strBuilder.append(" = ");
            strBuilder.append(carrinhoProduto.getIdProduto());
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            
            ResultSet rs = preparedStmt.executeQuery();
            List<CarrinhoProdutoDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<CarrinhoProdutoDTO> mountList(ResultSet rs) {
		List<CarrinhoProdutoDTO> listObj = new ArrayList<CarrinhoProdutoDTO>();
        try {
            while (rs.next()) {
            	CarrinhoProdutoDTO obj = new CarrinhoProdutoDTO();
            	
                obj.setIdCarrinho(rs.getInt(1));
                obj.setIdProduto(rs.getInt(2));
                obj.setQuantidade(rs.getInt(3));
                
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
}
