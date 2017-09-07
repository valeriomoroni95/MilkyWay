package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Vector;

import entity.Satellite;

public class SatelliteDao {
	
	public Vector<Satellite> showSatellites() throws SQLException {
        
        Connection connection  = null;
        Statement statement = null;
        Vector<Satellite> satellites = new Vector<Satellite>();    
        ResultSet result = null;
         
        final String query = "SELECT * FROM \"satellite\" s join \"tool_satellite\" ts on s.satellite_id = ts.satellite_id "+
        			"join tool t on ts.tool_id = t.tool_id order by s.satellite_id  ;"; 
            
        try {                
        	DataSource d = new DataSource();
        	connection = d.getConnection();                
        	statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(query);
            String currSatelliteName = null;
            String start = null;
            String end = null;
            Vector<String> tools = new Vector<String>();
            Satellite s = null;
            
            if (!result.first()) {
                  return null;
            }
             
            while(result.next()) {
            	
            	if(currSatelliteName != result.getString("satelliteName")) {	
            		
            		if (currSatelliteName != null) {
            			s = new Satellite(currSatelliteName, start, end, tools);
                    	satellites.add(s);
            			}
            		
                	currSatelliteName = result.getString("satelliteName");
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
	
	public static boolean isSatellitePresent(String name, String start, String end, Vector<String> tools, Vector<String> agencies){
		
		Connection connection = null;
        Statement statement = null;
		PreparedStatement pstatement = null;
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
            
            if(result.first()) {
            	
            		return false;
            		
            }else{
            	
                String insert = "INSERT INTO \"satellite\" VALUES ('"+start+"','"+end+"','"+name+"');";
                statement.executeUpdate(insert);
                
                String insert2 = "INSERT INTO \"tool_satellite\"(tool_name, satellite_name) VALUES (?,?);"; 
                pstatement = connection.prepareStatement(insert2);
				
            		for(String tool : tools) {
            			pstatement.setString(1, tool);
            			pstatement.setString(2, name);
            			pstatement.executeUpdate();
            		}
            	
            		String insert3 = "INSERT INTO \"agency_satellite\"(agency_id, satellite_name) VALUES (?,?);"; 
            		pstatement = connection.prepareStatement(insert3);
            		
            		for(String agency : agencies) { 
            			pstatement.setString(1, name);
            			pstatement.setInt(2, Integer.parseInt(agency));
            			pstatement.executeUpdate();
            		}
            		
           		result.close();
           		statement.close();
           		connection.close();
            
            }
            
        }catch(Exception e){
        		e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            	se2.printStackTrace();
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

