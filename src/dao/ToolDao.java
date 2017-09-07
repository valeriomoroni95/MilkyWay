package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import entity.Tool;

public class ToolDao {
		
	public boolean addTool(Tool tool) {
		
		Connection connection = null;
        PreparedStatement statement = null;
        
        
        final String query = "INSERT INTO \"tool\"(name, map) VALUES(?,?);";
		
        try {                
        	DataSource d = new DataSource();
        	connection = d.getConnection();                
        	statement = connection.prepareStatement(query);
        	statement.setString(1, tool.getToolName());
        	statement.setInt(2, tool.getMapId());
        	statement.executeUpdate();
          
        	final String query2 = "INSERT INTO \"tool_band\"(tool_name, band_resolution) values (?,?);"; //TODO sistemare statement
        	statement = connection.prepareStatement(query2);
        	for(Double band : tool.getBandList()) {
        		statement.setString(1, tool.getToolName());
        		statement.setDouble(2, band);
        		statement.executeUpdate();
        	}
        	statement.close();
        	connection.close();
        
        } catch (SQLException se) {
        // Errore durante l'apertura della connessione
        	se.printStackTrace();
          } catch(Exception e) {
        	  System.out.println("ToolDao.java: catch after try");
        	  return false;
        		} 
        
      return true;
	}
	
	
public Vector<String> showToolNames() throws SQLException {
        
        Connection connection = null;
        Statement statement = null;
        Vector<String> toolNames = new Vector<String>();    
        ResultSet result = null;
         
        final String query = "SELECT tool_name FROM \"tool\";"; 
        
        try {                
        	DataSource d = new DataSource();
        	connection = d.getConnection();                
        	statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(query);
            
            while(result.next()){
            	toolNames.add(result.getString("tool_name"));
            	
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
            
            return toolNames;
   }

public Vector<Tool> showTools() throws SQLException {
    
    Connection connection = null;
    Statement statement = null;
    Vector<Tool> tools = new Vector<Tool>();    
    ResultSet result = null;
     
    final String query = "SELECT * FROM \"tool\";"; 
    
    try {                
    	DataSource d = new DataSource();
    	connection = d.getConnection();                
    	statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        result = statement.executeQuery(query);
        
        while(result.next()){
        	Tool t = new Tool(result.getString("name"),result.getInt("map"));
        	tools.add(t);
        	
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
        
        return tools;
}

public static boolean isToolPresent(String name, int mapId, Vector<Double> bands) {
	
	Connection connection = null;
    Statement statement = null;
	PreparedStatement pstatement = null;
    ResultSet result = null;
    
    final String query = "SELECT \"name\" FROM \"tool\" WHERE \"name\" = '"+name+"';";
	
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
            String insert = "INSERT INTO \"tool\" VALUES ('"+name+"',?);";
            pstatement = connection.prepareStatement(insert);
            pstatement.setInt(1, mapId);
            pstatement.executeUpdate();
            String insert2 = "INSERT INTO \"tool_band\"(tool_name, band_resolution) VALUES (?,?);";
        	 
            pstatement = connection.prepareStatement(insert2);
			
        	for(Double band : bands) { //TODO fix!
        		pstatement.setDouble(2, band);
        		pstatement.setString(1, name);
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

