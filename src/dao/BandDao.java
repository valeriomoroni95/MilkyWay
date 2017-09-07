package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class BandDao {

public Vector<Double> showBandResolutions() throws SQLException {
        
        Connection connection = null;
        Statement statement = null;
        Vector<Double> resolutions = new Vector<Double>();    
        ResultSet result = null;
         
        final String query = "SELECT resolution FROM \"band\";"; 
        
        try {                
        	DataSource d = new DataSource();
        	connection = d.getConnection();                
        	statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(query);
            
            while(result.next()){
            	resolutions.add(result.getDouble("resolution"));
            	
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
            
            return resolutions;
   }
	
	
}
