package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        int HIGAL = 1;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        	
        	DataSource d = new DataSource();
            connection = d.getConnection();
            final String query = "INSERT INTO \"clump\"(clump_id ,g_lon, g_lat, k_temp, ratio, surf_dens, c_type, map_id) VALUES (?,?,?,?,?,?,?,?)";
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
            			String sql1 = "UPDATE clump SET(g_lon, g_lat, k_temp, ratio, surf_dens, c_type) = (?,?,?,?,?,?,?,?) WHERE clump_id =?;";
            			statement = connection.prepareStatement(sql1);
            			statement.setInt(6, clumpId);
                        statement.setDouble(1, gLon);
            			statement.setDouble(2, gLat);
            			statement.setDouble(3, temp);
            			statement.setDouble(4, ratio);
            			statement.setDouble(5, surfDens);
            			statement.setInt(6, cType);
            			statement.executeUpdate();
            		}
            		else{
            			String sql2 = "INSERT INTO clump (clump_id ,g_lon, g_lat, k_temp, ratio, surf_dens, c_type, map_id) VALUES (?,?,?,?,?,?,?,?);";
            			statement = connection.prepareStatement(sql2);
            			statement.setInt(1, clumpId);
                        statement.setDouble(2, gLon);
            			statement.setDouble(3, gLat);
            			statement.setDouble(4, temp);
            			statement.setDouble(5, ratio);
            			statement.setDouble(6, surfDens);
            			statement.setInt(7, cType);
            			statement.setInt(8,HIGAL);
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

  public boolean importSource(String filename) throws ClassNotFoundException, SQLException{
	
	Connection connection = null;
	Statement statement1 = null;
	PreparedStatement pstatement = null;
	PreparedStatement p2statement = null;
	PreparedStatement p3statement = null;
	SourceDao sd = new SourceDao();
	String line = "";
    String csvSplitBy = ",";
    int rowIndex = 0;
    
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
    	
    	DataSource d = new DataSource();
        connection = d.getConnection();
        //final String query = "INSERT INTO source(source_id ,brightness, longitude, latitude, map_id, source_mapcode, source_x) VALUES (?,?,?,?,4,?,?);";
        //pstatement = connection.prepareStatement(query);
        System.out.println("importSource: ho fatto la query");
        double longitude;
        double latitude;
        double fluxValue, fluxError;
        String source_x = null;
        String source_mapcode;
        int sourceId;
        int MIPSGAL = 3;
        while ((line = br.readLine()) != null) {

        	if(rowIndex++ > 11){
        		
        		String[] vect = line.split(csvSplitBy);
        		
        		longitude = Double.parseDouble(vect[1]);
        		latitude = Double.parseDouble(vect[2]);
        		source_mapcode = vect[0];
        		fluxValue = Double.parseDouble(vect[3]);
        		fluxError = Double.parseDouble(vect[4]);
        		if(vect.length == 6 )
        			source_x = vect[5];
        		else
        			source_x = null;
        		
        		if(sd.isPresent(source_mapcode)){
        			System.out.println("importSource: sto nell'if");
        			String sql = "SELECT source_id FROM source WHERE source_mapcode = '"+source_mapcode+"';";
        			statement1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);        			
        			System.out.println("ho fatto connection.prepareStatement()");
        			ResultSet rs = statement1.executeQuery(sql);
        			System.out.println("ho fatto la query con mapcode");
        			
        			if(rs.first()){
        				System.out.println("Sto nel secondo if");
        				sourceId = rs.getInt("source_id");
        		  			
        		
        			String sql1 = "UPDATE source SET(brightness, longitude, latitude, source_mapcode, source_x) = (?,?,?,?,?) WHERE source_id = ?;";
        			String sql2 = "UPDATE flux_source SET(value, error) = (?,?) where source_id = ? and band_resolution = ?;";

        			pstatement = connection.prepareStatement(sql1);
        			p2statement = connection.prepareStatement(sql2);
        			
        			
        			p2statement.setDouble(1, fluxValue);
        			p2statement.setDouble(2, fluxError);
        			p2statement.setInt(3, sourceId);
        			p2statement.setDouble(4, 24.0);

        			pstatement.setInt(6, sourceId);
        			pstatement.setDouble(1, Double.NaN);
        			pstatement.setDouble(2, longitude);
        			pstatement.setDouble(3, latitude);
        			pstatement.setString(4, source_mapcode);
        			pstatement.setString(5, source_x);
        			
        			System.out.println("Sono prima dell'update");
        			pstatement.executeUpdate();
        			p2statement.executeUpdate();
        			System.out.println("Sono dopo l'update");
        			}
        		}
        		else{
        			System.out.println("sto nell'else");
        		
        			String sql3 = "INSERT INTO source(source_id ,brightness, longitude, latitude, map_id, source_mapcode, source_x) VALUES (?,?,?,?,?,?,?);";
        			String sql4 = "INSERT INTO flux_source(band_resolution, source_id, value, error) VALUES (?,?,?,?);";
        			pstatement = connection.prepareStatement(sql3);
        			p2statement = connection.prepareStatement(sql4);
        			System.out.println("sto dopo prepare");

        			
        			System.out.println("sto dopo l'update dopo la insert");

        			String idQ = "SELECT source_id from source order by source_id DESC;";
                    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    ResultSet result = statement.executeQuery(idQ);
        			int sNext = 0;
                    if (result.next())
        				sNext = result.getInt("source_id")+ 1;
        			pstatement.setInt(1, sNext);
        			pstatement.setDouble(2, Double.NaN);
        			pstatement.setDouble(3, longitude);
        			pstatement.setDouble(4, latitude);
        			pstatement.setInt(5, MIPSGAL);
        			pstatement.setString(6, source_mapcode);
        			pstatement.setString(7, source_x);
        			
        			p2statement.setDouble(1, 24.0);
        			p2statement.setInt(2, sNext);
        			p2statement.setDouble(3, fluxValue);
        			p2statement.setDouble(4, fluxError);
        			pstatement.executeUpdate();
        			p2statement.executeUpdate();

        			
        		}
        	}
        }
        	
        }catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
    return true;    
    
}

/*public static void main(String args[]) throws ClassNotFoundException, SQLException {
	FileImportDao dao = new FileImportDao();
	dao.importSatellite("/home/luca/Scrivania/higal.csv");
		
}*/

}
       