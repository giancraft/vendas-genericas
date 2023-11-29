package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DBConnection.SQLConnection;
import DTO.ContaDTO;
import DTO.MarcaDTO;
import DTO.ProdutoDTO;

public class ProdutoDAO {

	protected String primaryKey = "idProduto";
	public String table = "produto";
    
    public List<String> fillable = new ArrayList<String>(Arrays.asList(
    		"nome",
    		"preco",
    		"estoque",
    		"descricao",
    		"idMarca",
    		"idVendedor"
	));
    
    public String getTable() {
		return table;
	}

	public List<String> getFillable() {
		return fillable;
	}

	public boolean create(ProdutoDTO produto) {
    	try {
    		Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            
            strBuilder.append("INSERT INTO ");
            strBuilder.append(getTable());
            strBuilder.append(fillable.toString().replace('[', '(').replace(']', ')'));
            strBuilder.append(" VALUES (?, ?, ?, ?, ?, ?)");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            preparedStmt.setString(1, produto.getNome());
            preparedStmt.setDouble(2, produto.getPreco());
            preparedStmt.setInt(3, produto.getEstoque());
            preparedStmt.setString(4, produto.getDescricao());
            preparedStmt.setInt(5, produto.getMarca());
            preparedStmt.setInt(6, produto.getVendedor());
            
            preparedStmt.executeUpdate();
            preparedStmt.close();
            conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
	public boolean update(ProdutoDTO produto) {
		try {
			Connection conn = SQLConnection.connect();
			StringBuilder strBuilder = new StringBuilder();
			
            strBuilder.append("UPDATE ");
            strBuilder.append(getTable());
            strBuilder.append(" SET nome = ?, preco = ?, estoque = ?, descricao = ?, idMarca = ?, idVendedor = ? WHERE  ");
            strBuilder.append(this.primaryKey);
            strBuilder.append(" = ");
            strBuilder.append(produto.getId());
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
           
            preparedStmt.setString(1, produto.getNome());
            preparedStmt.setDouble(2, produto.getPreco());
            preparedStmt.setInt(3, produto.getEstoque());
            preparedStmt.setString(4, produto.getDescricao());
            preparedStmt.setInt(5, produto.getMarca());
            preparedStmt.setInt(6, produto.getVendedor());
            
            int affectedRows = preparedStmt.executeUpdate();
            
            preparedStmt.close();
            conn.close();
            
            return affectedRows > 0;
		} catch (Exception e) {
	       	 e.printStackTrace();
	         return false;
	    }
	}
	public boolean delete(ProdutoDTO produto) {
		try {
			Connection conn = SQLConnection.connect();
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("DELETE FROM ");
			strBuilder.append(getTable());
			
            strBuilder.append(" WHERE ");
            strBuilder.append(this.primaryKey);
            strBuilder.append("= ");
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

	public List<ProdutoDTO> get() {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            ResultSet rs = preparedStmt.executeQuery();
            List<ProdutoDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<ProdutoDTO> getByVendedor(int idVendedor) {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            strBuilder.append(" WHERE idVendedor ");
            strBuilder.append(" = ?");
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            preparedStmt.setInt(1, idVendedor);
            ResultSet rs = preparedStmt.executeQuery();
            List<ProdutoDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<ProdutoDTO> find(ProdutoDTO produto) {
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
            List<ProdutoDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<ProdutoDTO> getByMarca(MarcaDTO marca,int idVendedor) {
        try {
            Connection conn = SQLConnection.connect();
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM ");
            strBuilder.append(getTable());
            strBuilder.append("	WHERE idMarca = ? AND idVendedor = ?");
            
            PreparedStatement preparedStmt = conn.prepareStatement(strBuilder.toString());
            
            preparedStmt.setInt(1, marca.getId());
            preparedStmt.setInt(2, idVendedor);
            ResultSet rs = preparedStmt.executeQuery();
            List<ProdutoDTO> listObj = mountList(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<ProdutoDTO> mountList(ResultSet rs) {
		List<ProdutoDTO> listObj = new ArrayList<ProdutoDTO>();
        try {
            while (rs.next()) {
            	ProdutoDTO obj = new ProdutoDTO();
            	
                obj.setId(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setPreco(rs.getDouble(3));
                obj.setEstoque(rs.getInt(4));
                obj.setDescricao(rs.getString(5));
                obj.setMarca(rs.getInt(6));
                obj.setVendedor(rs.getInt(7));
                
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
}
