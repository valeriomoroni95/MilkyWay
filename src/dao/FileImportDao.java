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
import java.lang.Object;

public class FileImportDao {
	
	public boolean importHigal(String filename) throws ClassNotFoundException, SQLException {
    	
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
            			String sql1 = "UPDATE clump SET(g_lon, g_lat, k_temp, ratio, surf_dens, c_type) = (?,?,?,?,?,?) WHERE clump_id =?;";
            			statement = connection.prepareStatement(sql1);
            			statement.setInt(7, clumpId);
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
                    if (result.first())
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
  
  public boolean importGlimpse(String filename) throws ClassNotFoundException, SQLException{
		
		Connection connection = null;                    
		Statement statement1 = null;
		PreparedStatement pstatement = null;
		PreparedStatement pstatement2 = null;
		PreparedStatement pstatement3 = null;
		PreparedStatement pstatement4 = null;
		PreparedStatement pstatement5 = null;

		SourceDao sd = new SourceDao();
		String line = "";
	    String csvSplitBy = ",";
	    int rowIndex = 0;
	    final int GLIMPSE = 2;
	    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	    	
	    	DataSource d = new DataSource();
	        connection = d.getConnection();     
	        double longitude;                
	        double latitude;         
	        String source_x = null;
	        String source_mapcode;
	        int sourceId;
	        int sId = 0;
	        Double f3_6 = -1.0; //se una riga è vuota, il valore rimane a -1 (che non è una banda accettabile!)
	        Double f4_5 = -1.0;
	        Double f5_8 = -1.0;
	        Double f8 = -1.0;
	        
	        while ((line = br.readLine()) != null) {
	
	        	if(rowIndex++ > 11){ 
	        		
	        		String[] vect = line.split(csvSplitBy);
	        		if(vect[3]!=null && !vect[3].trim().isEmpty())
	        			f3_6 = Double.parseDouble(vect[3]);
	        		if(vect[4]!=null && !vect[4].trim().isEmpty())
	        			f4_5 = Double.parseDouble(vect[4]);
	        		if(vect[5]!=null && !vect[5].trim().isEmpty())
	        			f5_8 = Double.parseDouble(vect[5]);
	        		if(vect[6]!=null && !vect[6].trim().isEmpty())
	        			f8 = Double.parseDouble(vect[6]);


	        		longitude = Double.parseDouble(vect[1]);
	        		latitude = Double.parseDouble(vect[2]);
	        		source_mapcode = vect[0]; //il mapcode qui è solo di Glimpse
	        		
	        		if(sd.isPresent(source_mapcode)){   //se il mapcode è già presente nel db, prendi il suo id e fai l'update dopo
	        			String sql = "SELECT source_id FROM source WHERE source_mapcode = '"+source_mapcode+"';";
	        			statement1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);                 			     ResultSet rs = statement1.executeQuery(sql);
	        			
	        			if(rs.first()){
	        				sourceId = rs.getInt("source_id");
	        		  		
		        			String sqlu1 = "UPDATE source SET(longitude, latitude, source_mapcode, source_x) = (?,?,?,?) WHERE source_id = ?;";
		        			String sqlu2 = "UPDATE flux_source SET value = ? WHERE band_resolution = ? and source_id = ?;";
		        			pstatement = connection.prepareStatement(sqlu1);
		        			pstatement.setInt(5, sourceId);
		        			pstatement.setDouble(1, longitude);
		        			pstatement.setDouble(2, latitude);
		        			pstatement.setString(3, source_mapcode);
		        			pstatement.setString(4, source_x);
		        			System.out.println("Sono prima dell'update");
		        			pstatement.executeUpdate();
		        			System.out.println("Sono dopo l'update");
		        			
		        			if(f3_6 != -1.0) {
			        			pstatement2 = connection.prepareStatement(sqlu2);
			        			pstatement2.setDouble(2, 3.6);
			        			pstatement2.setInt(3, sId);
			        			pstatement2.setDouble(1, f3_6);
			        			pstatement2.executeUpdate();
		        			}
		        			if(f4_5 != -1.0) {
			        			pstatement3 = connection.prepareStatement(sqlu2);
			        			pstatement3.setDouble(2, 4.5);
			        			pstatement3.setInt(3, sId);
			        			pstatement3.setDouble(1, f4_5);
			        			pstatement3.executeUpdate();

		        			}
		        			if(f5_8 != -1.0) {
			        			pstatement4 = connection.prepareStatement(sqlu2);
			        			pstatement4.setDouble(2, 5.8);
			        			pstatement4.setInt(3, sId);
			        			pstatement4.setDouble(1, f5_8);
			        			pstatement4.executeUpdate();

		        			}
		        			if(f8 != -1.0) {
			        			pstatement5 = connection.prepareStatement(sqlu2);
			        			pstatement5.setDouble(2, 8.0);
			        			pstatement5.setInt(3, sId);
			        			pstatement5.setDouble(1, f8);
			        			pstatement5.executeUpdate();

		        			}
		        		}
	        		}
	        		else{
	        			System.out.println("sto nell'else");
	        		
	        			String sql2 = "INSERT INTO source(source_id ,brightness, longitude, latitude, map_id, source_mapcode, source_x) VALUES (?,?,?,?,?,?,?);"; 
	        			String sql3 = "INSERT INTO flux_source(band_resolution, source_id, value, error) VALUES(?,?,?,?);";
	        			pstatement = connection.prepareStatement(sql2);
	        			System.out.println("sto dopo prepare");
		
	        			String idQ = "SELECT source_id from source order by source_id DESC;"; //Questa dovrebbe comunque andarci
	                    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	                    ResultSet result = statement.executeQuery(idQ);
	        			if (result.first())
	        				sId = result.getInt("source_id")+ 1;
		        			pstatement.setInt(1, sId);
		        			pstatement.setDouble(2, Double.NaN); 
		        			pstatement.setDouble(3, longitude);
		        			pstatement.setDouble(4, latitude);
		        			pstatement.setInt(5, GLIMPSE);
		        			pstatement.setString(6, source_mapcode);
		        			pstatement.setString(7, source_x);
		        			pstatement.executeUpdate();
		        			if(f3_6 != -1.0) {
			        			pstatement2 = connection.prepareStatement(sql3);
			        			pstatement2.setDouble(1, 3.6);
			        			pstatement2.setInt(2, sId);
			        			pstatement2.setDouble(3, f3_6);
			        			pstatement2.setDouble(4, 0.0);
			        			pstatement2.executeUpdate();
		        			}
		        			if(f4_5 != -1.0) {
			        			pstatement3 = connection.prepareStatement(sql3);
			        			pstatement3.setDouble(1, 4.5);
			        			pstatement3.setInt(2, sId);
			        			pstatement3.setDouble(3, f4_5);
			        			pstatement3.setDouble(4, 0.0);
			        			pstatement3.executeUpdate();

		        			}
		        			if(f5_8 != -1.0) {
			        			pstatement4 = connection.prepareStatement(sql3);
			        			pstatement4.setDouble(1, 5.8);
			        			pstatement4.setInt(2, sId);
			        			pstatement4.setDouble(3, f5_8);
			        			pstatement4.setDouble(4, 0.0);
			        			pstatement4.executeUpdate();

		        			}
		        			if(f8 != -1.0) {
			        			pstatement5 = connection.prepareStatement(sql3);
			        			pstatement5.setDouble(1, 8.0);
			        			pstatement5.setInt(2, sId);
			        			pstatement5.setDouble(3, f8);
			        			pstatement5.setDouble(4, 0.0);
			        			pstatement5.executeUpdate();

		        			}
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
  
  public boolean importHigalAdditionalInfo(String filename) throws ClassNotFoundException, SQLException {
  	
  		Connection connection = null;
		PreparedStatement statement = null;
		ClumpDao cd = new ClumpDao();
		String line = "";
		String csvSplitBy = ",";
		Double[] bands = {70.0, 160.0, 250.0, 350.0, 500.0};

		int rowIndex = 0;
		int HIGAL = 1;  //dipende dall'id che abbiamo nel db per HIGAL
		double f70,f160,f250,f350,f500,e70M,e70m,e160M,e160m,e250M,e250m,e350M,e350m,e500M,e500m,a70,a160,a250,a350,a500;
      try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      	
      	  DataSource d = new DataSource();
          connection = d.getConnection();
          //final String query = "INSERT INTO \"clump\"(clump_id ,g_lon, g_lat, k_temp, ratio, surf_dens, c_type, map_id) VALUES (?,?,?,?,?,?,?,?)";
          int clumpId;                                     //a 70, 160 e 250, 350 e 500. Sono 5 colonne ogni misurazione. Bisogna anche prendere 
          /*double gLon;                                     //l'angolo dell'ellisse a 70, 160, 250, 300 e 500 microns.
          double gLat;
          double temp;  //presumo non ci serviranno queste informazioni, dato che il clump è sempre lo stesso
          double ratio;
          double surfDens;
          int cType;*/
          
          while ((line = br.readLine()) != null) {

          	if(rowIndex++ > 27){
          		
          		String[] vect = line.split(csvSplitBy);
          		
          		clumpId = Integer.parseInt(vect[0]);
          		/*f70 = Double.parseDouble(vect[1]);
          		f160 = Double.parseDouble(vect[2]);
          		f250 = Double.parseDouble(vect[3]);
          		f350 = Double.parseDouble(vect[4]);
          		f500 = Double.parseDouble(vect[5]);
          		e70M = Double.parseDouble(vect[6]);
          		e70m = Double.parseDouble(vect[7]);
          		e160M = Double.parseDouble(vect[8]);
          		e160m = Double.parseDouble(vect[9]);
          		e250M = Double.parseDouble(vect[10]);
          		e250m = Double.parseDouble(vect[11]);
          		e350M = Double.parseDouble(vect[12]);
          		e350m = Double.parseDouble(vect[13]);
          		e500M = Double.parseDouble(vect[14]);
          		e500m = Double.parseDouble(vect[15]);
          		a70 = Double.parseDouble(vect[16]);
          		a160 = Double.parseDouble(vect[17]);
          		a250 = Double.parseDouble(vect[18]);
          		a350 = Double.parseDouble(vect[19]);
          		a500 = Double.parseDouble(vect[20]);*/

          		
          			String sql1 = "UPDATE flux_clump SET(value, error) = (?,?) where clump_id = ? and band_resolution = ?;";
          			String sql3 = "INSERT INTO flux_clump(band_resolution, clump_id, value, error) VALUES (?,?,?,?);";
      				
          			if(cd.isPresent(clumpId)) {

      					for(int i=1; i<6; i++) {
	          				if(cd.isFluxPresent(clumpId, bands[i-1])){
			          			statement = connection.prepareStatement(sql1);   //l'update sarà basato sulle varie misurazioni. Contemporaneamente 
			          			statement.setDouble(1, Double.parseDouble(vect[i]));
			          			statement.setDouble(2, 0.0);
			          			statement.setInt(3, clumpId);
			          			statement.setDouble(4, bands[i-1]);
			          			statement.executeUpdate();
	          				}
	          				else {
	          					statement = connection.prepareStatement(sql3);   
	    	          			statement.setDouble(1, bands[i-1]);
	    	          			statement.setInt(2, clumpId);
	                  			statement.setDouble(3, Double.parseDouble(vect[i]));
	    	          			statement.setDouble(4, 0.0);
	    	          			statement.executeUpdate();
		
	          				}
	          				
	          			}
	          			String sql4 = "INSERT INTO ellipse(band_resolution, clump_id,  x_axis, y_axis, angle) VALUES (?,?,?,?,?);";
	          			String sql2 = "UPDATE ellipse SET(x_axis, y_axis, angle) = (?,?,?) where clump_id = ? and band_resolution = ?;";
	          			
	          			for(int i=6; i<15; i+=2) {
	          				if(cd.isEllipsePresent(clumpId, bands[i/2 - 3])) {
			          			statement = connection.prepareStatement(sql2);   //l'update sarà basato sulle varie misurazioni. Contemporaneamente 
			          			statement.setDouble(1, Double.parseDouble(vect[i]));
			          			statement.setDouble(2, Double.parseDouble(vect[i+1]));
			          			statement.setDouble(3, Double.parseDouble(vect[i/2+13]));
			          			statement.setInt(4, clumpId);
			          			statement.setDouble(5, bands[i/2 - 3]);
			          			statement.executeUpdate();
	          				}
		          			else {
			          			statement = connection.prepareStatement(sql4);   //l'update sarà basato sulle varie misurazioni. Contemporaneamente 
			          			statement.setDouble(3, Double.parseDouble(vect[i]));
			          			statement.setDouble(4, Double.parseDouble(vect[i+1]));
			          			statement.setDouble(5, Double.parseDouble(vect[i/2+13]));
			          			statement.setInt(2, clumpId);
			          			statement.setDouble(1, bands[i/2-3]);
			          			statement.executeUpdate();
		          			}
	          			}
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
	dao.importHigalAdditionalInfo("/home/luca/Scrivania/higal_additionalinfo.csv");
	}
}
       