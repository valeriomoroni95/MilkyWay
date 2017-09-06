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
        	statement.setString(1, tool.getToolName());
        	statement.setDouble(2, tool.getResolution());
        	statement.executeUpdate();
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


}

