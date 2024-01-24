package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
	final static String HOST = "";
	final static String NOME_DO_BANCO = "";
	final static String USER = "";
	final static String PASS = "";
    public static Connection connect() {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://"+HOST+"/"+NOME_DO_BANCO;
            return DriverManager.getConnection(url,USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
