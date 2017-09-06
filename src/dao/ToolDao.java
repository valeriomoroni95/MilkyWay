package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import entity.Tool;

public class ToolDao {
	
	private static DataSource dataSource;
	
	public boolean addTool(Tool tool) {
		
		
		
		Connection connection = null;
        PreparedStatement statement = null;
        
        
        final String query = "INSERT INTO \"tool\"(name, map) VALUES(?,?)";
		
        try {                
        	DataSource d = new DataSource();
        	connection = d.getConnection();                
        	statement = connection.prepareStatement(query);
        	statement.setString(1, tool.getToolName());
        	statement.setInt(2, tool.getMapId());
        	statement.executeUpdate();
           
          
        }catch(Exception e) {
      	  System.out.println("ToolDao.java: catch after try");
      	  return false;
        } 
      
      return true;
	}

}

