package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import entity.Satellite;

public class SatelliteDao {
	
	private static DataSource dataSource;
	
	public Vector<Satellite> showSatellites() throws SQLException {
        
        Connection connection = null;
        Statement statement = null;
        Vector<Satellite> satellites = new Vector<Satellite>();    
        ResultSet result = null;
         
        final String query = "SELECT * FROM \"satellite\";"; /*per la visualizzazione sul sito di tutti i
        satelliti; se però ci si vogliono scrivere anche gli strumenti diventa complicatino   */
            
        try {                
        	DataSource d = new DataSource();
        	connection = d.getConnection();                
        	statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(query);
                
            if (!result.first()) {
                  return null;
            }
            
            while(result.next()) {
            	Satellite s = new Satellite(result.getInt("satellite_id"), result.getString("satelliteName"), result.getString("satelliteStart"), result.getString("satelliteEnd"));
            	//TODO cambiare i nomi nelle result.getXxx secondo quelli del db, cambiare tipo di dati nel db "date"-->"string"
            	satellites.add(s);
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
}
