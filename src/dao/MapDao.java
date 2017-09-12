package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import entity.Map;

public class MapDao {
	
    public Vector<Map> showMaps() throws SQLException {
        
        Connection connection = null;
        Statement statement = null;
        Vector<Map> maps = new Vector<Map>();    
        ResultSet result = null;
         
        final String query = "SELECT m.map_id as mid, m.name as mapname FROM \"map\" m;"; //voglio selezionare id mappa e nome mappa per tutte le mappe
            
        try {                
        		DataSource d = new DataSource();
        		connection = d.getConnection();                
        		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        		result = statement.executeQuery(query);
            
            while(result.next()){
            		Map m = new Map(result.getInt("mid"),result.getString("mapname"));
            		maps.add(m);
            	
            }
                
            if (!result.first()) {
                  return null;
            } 
            
         }catch (Exception e){
        	
        		System.out.println("MapDao.java: catch after try");

            e.printStackTrace();

        } finally {
        	
        		System.out.println("MapDao.java: finally");

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
            
            return maps;
      }

 public Vector<String> showMapNames() throws SQLException { //per checkbox con nomemappa, quando non serve inserire
	 														//nulla a posteriori nel db (nomemappa != idmappa)
      
        Connection connection = null;
        Statement statement = null;
        Vector<String> mapNames = new Vector<String>();    
        ResultSet result = null;
         
        final String query = "SELECT DISTINCT name FROM \"map\";"; //voglio selezionare id mappa e nome mappa per tutte le mappe
            
        try {                
        		DataSource d = new DataSource();
        		connection = d.getConnection();                
        		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(query);
            
            while(result.next()){
            		mapNames.add(result.getString("name"));
            }
                
            if (!result.first())
                  return null; 
            
         }catch (Exception e){
        	 
        	 	e.printStackTrace();
        	 	
         } finally {
         		if (result != null)
         			result.close();
         		if (statement != null)
         			statement.close();
         		if (connection != null)
         			connection.close();
         		}
            
            return mapNames;
     }

    public static boolean addMap(String mapId, String mapName) {
   	 
    	Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        
        final String query = "SELECT \"map_id\" FROM \"map\" WHERE \"map_id\" = '"+mapId+"';";
        
        try {
       	 	DataSource d = new DataSource();
            connection = d.getConnection();
            System.out.println("MapDao.java: addMap.java: sto richiedendo una connessione" + connection);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            System.out.println("MapDao.java: addMap.java: ho preparato una statement" + statement);
            result = statement.executeQuery(query);
            System.out.println("MapDao.java: addMap.java: ho un result" + result);
            
            if (result == null) {
           	 System.out.println("MapDao.java: errore nel primo if");
                return false;
            }
            
            if(result.first())
           	 return false;
            
            else { //TODO fix!
                String sql1 = "INSERT INTO \"map\"(map_id, name) VALUES ('"+mapId+"','"+mapName+"');";
                System.out.println("ho fatto la insert " + sql1);
                statement.executeUpdate(sql1);
            }
            result.close();
            statement.close();
            connection.close();
            
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
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
