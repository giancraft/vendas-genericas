package DAO;

import java.util.ArrayList;

abstract class DAO {
	
	protected String primaryKey = "id";

    protected String table;
    
    protected ArrayList<String> fillable;
    
}
