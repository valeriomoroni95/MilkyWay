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
        
        
        final String query = "INSERT INTO \"tool\"(tool_name, map_id) VALUES(?,?);";
		
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
            if(result.first()) {
            	do{
            		toolNames.add(result.getString("tool_name"));
            	}while(result.next());
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
     
    final String query = "SELECT t.tool_name as toolname, m.map_id as mid, m.name as mapname, " + 
    					 "tb.band_resolution as resolution FROM \"tool\" t join map m on t.map_id = m.map_id "+
    					 "join tool_band tb on tb.tool_name = t.tool_name order by t.tool_name;"; 
    try {                
    		DataSource d = new DataSource();
    		connection = d.getConnection();                
    		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        result = statement.executeQuery(query);
        
        String temp = "";
        String currName = "";
        String currMapName = "";
        int currMid = -1;
        Vector<Double> bands = new Vector<Double>();
        if(result.first()) {
	        do{
	        	temp = result.getString("toolname");
	        	if(!currName.equals(temp) && !currName.equals("")) {
	            	Tool t = new Tool(currName,currMid, currMapName, bands);
	            	tools.add(t);
	            	bands = new Vector<Double>();
	        	}
	        	currName = temp;
	        	currMapName = result.getString("mapname");
	        	currMid = result.getInt("mid");
	        	bands.add(result.getDouble("resolution"));
	        }while(result.next());
        }
        Tool t = new Tool(currName,currMid, currMapName, bands);
    	tools.add(t);
        
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
    
    final String query = "SELECT \"tool_name\" FROM \"tool\" WHERE \"tool_name\" = '"+name+"';";
	
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

