package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;


public class FileImportDao {
	
public boolean importSatellite(String filename) throws ClassNotFoundException, SQLException {
    	
    	Connection connection = null;
		PreparedStatement statement = null;
		ClumpDao cd = new ClumpDao();
		String line = "";
        String csvSplitBy = ",";
        int rowIndex = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        	
        	DataSource d = new DataSource();
            connection = d.getConnection();
            final String query = "INSERT INTO \"clump\"(clump_id ,g_lon, g_lat, k_temp, ratio, surf_dens, c_type, map_id) VALUES (?,?,?,?,?,?,?,3)";
            statement = connection.prepareStatement(query);
            int clumpId;
            double gLon;
            double gLat;
            double temp;
            double ratio;
            double surfDens;
            int cType;
            
            while ((line = br.readLine()) != null) {

            	if(rowIndex++ > 11){
            		
            		String[] vect = line.split(csvSplitBy);
            		clumpId = Integer.parseInt(vect[0]);
            		gLon = Double.parseDouble(vect[1]);
            		gLat = Double.parseDouble(vect[2]);
            		temp = Double.parseDouble(vect[3]);
            		ratio = Double.parseDouble(vect[4]);
            		surfDens = Double.parseDouble(vect[5]);
            		cType = Integer.parseInt(vect[6]);
            		if(cd.isPresent(clumpId)){
            			String sql1 = "UPDATE clump SET(clump_id ,g_lon, g_lat, k_temp, ratio, surf_dens, c_type, map_id) = (?,?,?,?,?,?,?,?,3);";
            			statement.setInt(1, clumpId);
                        statement.setDouble(2, gLon);
            			statement.setDouble(3, gLat);
            			statement.setDouble(4, temp);
            			statement.setDouble(5, ratio);
            			statement.setDouble(6, surfDens);
            			statement.setInt(7, cType);
            			statement.executeUpdate();
            		}
            		else{
            			String sql2 = "INSERT INTO clump VALUES(clump_id ,g_lon, g_lat, k_temp, ratio, surf_dens, c_type, map_id) VALUES (?,?,?,?,?,?,?,3);";
            			statement.setInt(1, clumpId);
                        statement.setDouble(2, gLon);
            			statement.setDouble(3, gLat);
            			statement.setDouble(4, temp);
            			statement.setDouble(5, ratio);
            			statement.setDouble(6, surfDens);
            			statement.setInt(7, cType);
            			statement.executeUpdate();
            		}
            	}
            	}
            }	 catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return true;
    }

public static void main(String args[]) throws ClassNotFoundException, SQLException {
	FileImportDao dao = new FileImportDao();
	dao.importSatellite("/Users/Valerio/Desktop/higal.csv");
		
}

}
       