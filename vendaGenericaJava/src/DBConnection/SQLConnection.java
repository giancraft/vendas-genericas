package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {

	final static String NOME_DO_BANCO = "vendasgenericas";
    public static Connection connect() {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/" + NOME_DO_BANCO;
            return DriverManager.getConnection(url,"root","");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
