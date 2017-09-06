package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import entity.Satellite;

public class SatelliteDao {
	
	public Vector<Satellite> showSatellites() throws SQLException {
        
        Connection connection = null;
        Statement statement = null;
        Vector<Satellite> satellites = new Vector<Satellite>();    
        ResultSet result = null;
         
        final String query = "SELECT * FROM \"satellite\" s join \"tool_satellite\" ts on s.satellite_id = ts.satellite_id join tool t on ts.tool_id = t.tool_id order by s.satellite_id  ;"; 
            
        try {                
        	DataSource d = new DataSource();
        	connection = d.getConnection();                
        	statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(query);
            int currentSatId = -1; 
            String satelliteName = null;
            String start = null;
            String end = null;
            List<String> tools = new ArrayList<String>();
            Satellite s = null;
            
            if (!result.first()) {
                  return null;
            }
             
            while(result.next()) {
            	
            	if(currentSatId != result.getInt("satellite_id")) {	
            		
            		if (currentSatId != -1) {
            			s = new Satellite(currentSatId, satelliteName, start, end, tools);
                    	satellites.add(s);
            			}
            		
            		currentSatId = result.getInt("satellite_id");
                	satelliteName = result.getString("satelliteName");
                	start = result.getString("satelliteStart");
                	end = result.getString("satelliteEnd");
                	tools = null;
            	}
            	
            	tools.add(result.getString("tool_name"));
            }
            
            return satellites;
        
        } catch(Exception e) {
    	  System.out.println("SatelliteDao.java: catch after try");
          } finally {
        	  System.out.println("SatelliteDao.java: finally");
        	  if(result != null)
        		  result.close();
        	  if(statement != null)
        		  statement.close();
        	  if(connection != null)
        		  connection.close();
          }
        
        return null;
	}

	public static boolean isSatellitePresent(String name, String start, String end){
		
		Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        
        final String query = "SELECT \"name\" FROM \"satellite\" WHERE \"name\" = '"+name+"';";
		
        try{
        	DataSource d = new DataSource();
            connection = d.getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(query);
            if (result == null) {
                return false;
            }
            
            if(result.first()){
            	return false;
            }else {
                String insert = "INSERT INTO \"satellite\" VALUES ('"+name+"','"+start+"','"+end+"');";
                statement.executeUpdate(insert);
            }
            result.close();
            statement.close();
            connection.close();
            
        }catch(Exception e){
        	e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
		return true;
		
	}

}
