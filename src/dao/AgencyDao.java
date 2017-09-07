package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import entity.Agency;

public class AgencyDao {
	
	public Vector<Agency> showAgencies() throws SQLException {
	    
	    Connection connection = null;
	    Statement statement = null;
	    Vector<Agency> agencies = new Vector<Agency>();    
	    ResultSet result = null;
	     
	    final String query = "SELECT * FROM \"agency\";"; 
	    
	    try {                
	    	DataSource d = new DataSource();
	    	connection = d.getConnection();                
	    	statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        result = statement.executeQuery(query);
	        
	        while(result.next()){
	        	Agency a = new Agency(result.getInt("id"), result.getString("name"));
	        	agencies.add(a);
	        	
	        }
	            
	        if (!result.first()) {
	              return null;
	        } 
	        
	     }catch (Exception e){
	    	
	    		System.out.println("ToolDao.java: catch after try");

	        e.printStackTrace();

	    } finally {
	    	
	    		System.out.println("ToolDao.java: finally");

	        if (result != null) {
	            result.close();
	        }

	        if (statement != null) {
	            statement.close();
	        }

	        if (connection != null) {
	            connection.close();
	        }
	    }
	        
	        return agencies;
	}
}