package tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DataSource;

public class ClumpT {

	public boolean isPresent(int clumpId) {
		Connection connection = null;
        PreparedStatement statement = null;
           	
        ResultSet result = null;
        	
        
        try {
       	 	
        	final String query = "SELECT \"clump_id\" FROM \"clump\" WHERE \"clump_id\" = ?;";
        	DataSource d = new DataSource();

            connection = d.getConnection();
            
            PreparedStatement pStatement = connection.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pStatement.setInt(1, clumpId);
            result = pStatement.executeQuery();

            
            
            if(result.first())
           	 return true;
          
            result.close();
            pStatement.close();
            connection.close();
            return false;  
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
