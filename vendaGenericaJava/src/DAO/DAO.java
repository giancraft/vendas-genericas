package DAO;

import java.util.ArrayList;

public class DAO {
	
	protected String primaryKey = "id";

    protected String table;
    
    protected ArrayList<String> fillable;
    
    protected ArrayList<String> timestamps;
    
    protected String deleteTimestamps;
}
