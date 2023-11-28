package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
	final static String HOST = "mysql16-farm2.uni5.net";
	final static String NOME_DO_BANCO = "areadeprojetos30";
	final static String USER = "areadeprojetos30";
	final static String PASS = "pedro1301";
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
