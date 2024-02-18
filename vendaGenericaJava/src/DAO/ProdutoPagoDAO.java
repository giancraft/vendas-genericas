package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import DBConnection.SQLConnection;
import DTO.ProdutoDTO;
import DTO.ProdutoPagoDTO;
import Interfaces.ProdutoPagoInterface;

public class ProdutoPagoDAO extends DAO implements ProdutoPagoInterface {
	protected String primaryKey = "idProdutoPago";
	public String table = "produtopago";
    
    public List<String> fillable = new ArrayList<String>(Arrays.asList(
    		"idPagamento",
    		"nome",
    		"preco",
    		"quantidade"
	));
    
    public String getTable() {
		return table;
	}

	public List<String> getFillable() {
		return fillable;
	}
	public boolean create(ProdutoPagoDTO produto) {
    	try {
    		Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            
            strBuilder.append("INSERT INTO ");
            strBuilder.append(getTable());
            strBuilder.append(fillable.toString().replace('[', '(').replace(']', ')'));
            strBuilder.append(" VALUES (?, ?, ?, ?)");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            preparedStmt.setInt(1, produto.getIdPagamento());
            preparedStmt.setString(2, produto.getNome());
            preparedStmt.setDouble(3, produto.getPreco());
            preparedStmt.setInt(4, produto.getQuantidade());
            
            preparedStmt.executeUpdate();
            preparedStmt.close();
            conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }

	public List<ProdutoPagoDTO> getByCarrinho(int id) {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            strBuilder.append("	WHERE idCarrinho = ? ");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            List<ProdutoPagoDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public boolean update(ProdutoPagoDTO produto) {
		try {
			Connection conn = SQLConnection.connect();
			StringBuilder strBuilder = new StringBuilder();
			
            strBuilder.append("UPDATE ");
            strBuilder.append(getTable());
            strBuilder.append(" SET nome = ?, preco = ?, quantidade = ? WHERE ");
            strBuilder.append(this.primaryKey);
            strBuilder.append(" = ");
            strBuilder.append(produto.getId());
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            preparedStmt.setString(1, produto.getNome());
            preparedStmt.setDouble(2, produto.getPreco());
            preparedStmt.setInt(3, produto.getQuantidade());
            
            int affectedRows = preparedStmt.executeUpdate();
            
            preparedStmt.close();
            conn.close();
            
            return affectedRows > 0;
		} catch (Exception e) {
	       	 e.printStackTrace();
	         return false;
	    }
	}
	public boolean delete(ProdutoPagoDTO produto) {
		try {
			Connection conn = SQLConnection.connect();
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("DELETE FROM ");
			strBuilder.append(getTable());
			
            strBuilder.append(" WHERE ");
            strBuilder.append(this.primaryKey);
            strBuilder.append(" = ");
            strBuilder.append(produto.getId());
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

	public List<ProdutoPagoDTO> get() {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            ResultSet rs = preparedStmt.executeQuery();
            List<ProdutoPagoDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<ProdutoPagoDTO> find(ProdutoDTO produto) {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            strBuilder.append("	WHERE ");
            strBuilder.append(this.primaryKey);
            strBuilder.append("= ?");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            
            preparedStmt.setInt(1, produto.getId());
            ResultSet rs = preparedStmt.executeQuery();
            List<ProdutoPagoDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public List<ProdutoPagoDTO> getByPagamento(int id) {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            strBuilder.append("	WHERE idPagamento");
            strBuilder.append("= ?");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            List<ProdutoPagoDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<ProdutoPagoDTO> mountList(ResultSet rs) {
		List<ProdutoPagoDTO> listObj = new ArrayList<ProdutoPagoDTO>();
        try {
            while (rs.next()) {
            	ProdutoPagoDTO obj = new ProdutoPagoDTO();
            	
                obj.setId(rs.getInt(1));
                obj.setIdPagamento(rs.getInt(2));
                obj.setNome(rs.getString(3));
                obj.setPreco(rs.getDouble(4));
                obj.setQuantidade(rs.getInt(5));
                
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
}
