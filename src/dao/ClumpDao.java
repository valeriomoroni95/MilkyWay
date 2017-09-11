package dao;

import java.sql.Connection;
import entity.Clump;
import entity.DistanceComparator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class ClumpDao {

	public boolean addClump(Clump clump) {
		
		Connection connection  = null;
        PreparedStatement statement = null;
        
        final String query = "INSERT INTO \"clump\"(clump_id ,g_lon, g_lat, k_temp, ratio, surf_dens, c_type) VALUES (?,?,?,?,?,?,?)";
        
        try{
        	DataSource d = new DataSource();
            connection = d.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, clump.getClump_id());
            statement.setDouble(2, clump.getG_lon());
			statement.setDouble(3, clump.getG_lat());
			statement.setDouble(4, clump.getK_temp());
			statement.setDouble(5, clump.getRatio());
			statement.setDouble(6, clump.getSurf_dens());
			statement.setInt(7, clump.getC_type());
			statement.executeUpdate();

        }catch (Exception e){
        	return false;
        	
        }
        return true;
	}
	
	public Vector<Clump> findMassiveStar() {
		
		Connection connection = null;
		ResultSet result = null;
		Statement statement = null;
		Vector<Clump> clumps = new Vector<Clump>();
		
		final String query = "SELECT * FROM \"clump\" WHERE \"surf_dens\">0.1 AND \"surf_dens\" <1.0";
		
		try {
        	
            DataSource d = new DataSource();
            connection = d.getConnection();
            
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(query);
            
            while(result.next()){
            	Clump clump = new Clump();
            	clump.setClump_id(result.getInt("id_clump"));
				clump.setSurf_dens(result.getDouble("surf_dens"));
				clump.setG_lat(result.getDouble("g_lat"));
				clump.setG_lon(result.getDouble("g_lon"));
				clump.setRatio(result.getDouble("ratio"));
				clump.setK_temp(result.getDouble("k_temp"));
				clump.setC_type(result.getInt("c_type"));
				clumps.add(clump);

            }
            statement.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return clumps;
	}
	
	public Vector<String[]> findClumpsInMap (String map,Double band){ 
    	Vector<String[]> v = null;
    	Connection connection = null;
        ResultSet result = null;
		v = new Vector<String[]>();

        String query = "SELECT DISTINCT c.clump_id, fc.value, fc.error FROM clump c join flux_clump fc on" +
        				" c.clump_id = fc.clump_id join map m on c.map_id = m.map_id WHERE m.name = '" + map + "' "; 
        if(!band.equals(0.0))
        		 query = query + "AND fc.band_resolution = ?";
        			        
        	query = query + " order by c.clump_id;";
        	
    	try { //TODO check || NB: è per il requisito 5
		
    		DataSource d = new DataSource();
        	connection = d.getConnection();
        	PreparedStatement pStatement = connection.prepareStatement(query);
        	pStatement.setDouble(1,band);
        	result = pStatement.executeQuery();
    		}
				catch (SQLException se) {
					se.printStackTrace();				}
    		catch(Exception e) {
    				e.printStackTrace();
      		} 
		
				String[] s = {"","","","","",""};
		    	try {
		    		if (!band.equals(0.0)) { 
		    			while(result.next()){
		    				s[0] = Integer.toString(result.getInt("clump_id"));
		    				s[1] = Double.toString(result.getDouble("g_lat"));
		    				s[2] = Double.toString(result.getDouble("g_lon"));
		    				s[3] = Double.toString(result.getDouble("resolution"));
		    				s[4] = Double.toString(result.getDouble("value")); 
		    				s[5] = Double.toString(result.getDouble("error"));
		    				

		    			//TODO salvare anche posizione spaziale? 
		    			v.add(s);
		    			}
		    		}
		    		else {
			    			int currId = -1;

			    			while(result.next()) {
			    				if(currId != result.getInt("clump_id")) {
			    				s[0] = Integer.toString(result.getInt("clump_id"));
			    				s[1] = Double.toString(result.getDouble("g_lat"));
			    				s[2] = Double.toString(result.getDouble("g_lon"));
			    				s[3] = Double.toString(result.getDouble("band_resolution"));
			    				s[4] = Double.toString(result.getDouble("value"));
			    				s[5] = Double.toString(result.getDouble("error"));
			    				v.add(s);
			    			
			    				}
			    				
			    				else {
			    				s[0] = " "; //il clump è lo stesso, non ristampo questi valori;
			    				s[1] = " "; //lo stesso vale per le coordinate.
			    				s[2] = " "; //ricordare di escludere queste righe dal conteggio dei 50 oggetti
			    				s[3] = Double.toString(result.getDouble("band_resolution"));
			    				s[4] = Double.toString(result.getDouble("value"));
			    				s[5] = Double.toString(result.getDouble("error"));
			    				v.add(s);
			    				}
			    			} 
		    			}
		    		}
		    		catch (SQLException e1) {
		    		e1.printStackTrace();
		    		}
		 	 return v;
	}
	
	
	public Vector<String[]> showClumpInfo(int clumpId) {
		
    	Vector<String[]> v = null;
    	Connection connection = null;
        ResultSet result = null;
        v = new Vector<String[]>();
        
        String query = "SELECT DISTINCT c.clump_id, c.g_lat, c.g_lon, fc.band_resolution, fc.value, fc.error FROM clump c join flux_clump fc on" +
        				"c.clump_id = fc.clump_id WHERE c.clump_id = ?;"; 
                	
    	try { //TODO check || NB: è per il requisito 6
		
    		DataSource d = new DataSource();
        	connection = d.getConnection();
        	PreparedStatement pStatement = connection.prepareStatement(query);
        	pStatement.setInt(1,clumpId);
        	result = pStatement.executeQuery(query);
    		}
			catch (SQLException se) {
					se.printStackTrace();				
			}	
    		catch(Exception e) {
    				e.printStackTrace();
      		} 
		
			
		    	try {
		    			String[] s = {"","",""};
		    			String[] t = {"","",""};
		    			if(result.next()) {
		    				s[0] = Integer.toString(result.getInt("clump_id"));
		    				s[1] = Double.toString(result.getDouble("g_lat"));
		    				s[2] = Double.toString(result.getDouble("g_lon"));
		    				v.add(s);
		    				t[0] = Double.toString(result.getDouble("band_resolution"));
		    				t[1] = Double.toString(result.getDouble("value"));
		    				t[2] = Double.toString(result.getDouble("error"));
		    				v.add(t);
		    				
		    			} /* Ho creato un array di stringhe da passare ->controller->bean: 
		    				funge da "Header";*/
		    			
		    			while(result.next()){
		    				
		    				t[0] = Double.toString(result.getDouble("band_resolution"));
		    				t[1] = Double.toString(result.getDouble("value"));
		    				t[2] = Double.toString(result.getDouble("error"));

		    				v.add(t);
		    			} /* Dopo l'header ci sono array di stringhe con banda, valore ed errore
		    			 	 per le diverse bande     */
		    		}
		    	catch (SQLException e1) {
		    		e1.printStackTrace();
		    	}
		 	 return v;
	}
//    for(String[] rawClumpData : fileData){

	public boolean isPresent(int clumpId) {
		Connection connection = null;
        PreparedStatement statement = null;
           	
        ResultSet result = null;
        	
        
        try {
       	 	
        	System.out.println("ClumpDao: isPresent.java: sto dopo il try");
        	final String query = "SELECT \"clump_id\" FROM \"clump\" WHERE \"clump_id\" = ?;";
        	DataSource d = new DataSource();
        	System.out.println("ClumpDao: isPresent.java: sopravvissuto al Datasource");

            connection = d.getConnection();
            
            PreparedStatement pStatement = connection.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pStatement.setInt(1, clumpId);
            result = pStatement.executeQuery();
            System.out.println("ClumpDao: isPresent.java: ho eseguito la query");

            
            
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
		
	
	public Vector<String[]> showClumpsInArea(Double latitude,Double longitude, Double lenght, boolean isCircle){
		
		//permette di cercare i clump che si trovano in un cerchio o un quadrato centrato in latitude, longitude
		// e con raggio/cateto di lunghezza lenght. Se cerco in un cerchio isCircle = true, rettangolo false
 		// l'array di stringhe ritornato nel vettore ha il formato [id, latitudine, longitudine, distanza]

		Vector<String[]> data = new Vector<String[]>();
		String query, condition;
    	Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet result;		
		
		try {
			
			query = "SELECT c.clump_id, c.g_lat, c.g_lon "  + 
					"FROM clump c;";
			
			DataSource d = new DataSource();
            connection = d.getConnection();
        	pStatement = connection.prepareStatement(query);
        	result = pStatement.executeQuery();
        	int clumpId;
        	Double lat, lon, distance;
        	while(result.next()) {
        		
        		clumpId = result.getInt("clump_id");
        		lat = result.getDouble("g_lat");
        		lon = result.getDouble("g_lon");
        		
        		if(isCircle) {
        			distance = Math.sqrt(Math.pow(latitude - lat, 2)+Math.pow(longitude - lon,2));
        			if(distance < lenght) {
        				String toPass[] = { Integer.toString(clumpId),
        									Double.toString(lat),
        									Double.toString(lon),
        									Double.toString(distance)
											};
        				data.add(toPass);
        			}
        		}
        		else {
        			if(lat < latitude + lenght/2 && lat > latitude - lenght/2 &&
        				lon < longitude + lenght/2 && lon > longitude - lenght/2) {
        					distance = Math.sqrt(Math.pow(latitude - lat, 2)+Math.pow(longitude -lon,2));
        					String toPass[] = { Integer.toString(clumpId),
        							Double.toString(lat),
        							Double.toString(lon),
        							Double.toString(distance)
        							};
                			data.add(toPass);
        			
        				}
        		}
        	}

			/*if(isCircle) {
				condition = "WHERE SQRT((c.g_lat - ?)*(c.g_lat - ?) + " + 
							"(c.g_lon - ?)*(c.g_lon - ?))  < ?;";
				query = query + condition;
	        	pStatement = connection.prepareStatement(query);

				pStatement.setDouble(1, latitude);
				pStatement.setDouble(2, latitude);
				pStatement.setDouble(3, longitude);
				pStatement.setDouble(4, longitude);
				pStatement.setDouble(5, lenght);
			}
			else {
				condition = "WHERE c.g_lat < ? AND c.g_lat > ? AND c.g_lon < ? AND c.g_lon > ?;";
				query = query + condition;
	        	pStatement = connection.prepareStatement(query);

				pStatement.setDouble(1, latitude);
				pStatement.setDouble(2, longitude);
				pStatement.setDouble(3, latitude + lenght/2);
				pStatement.setDouble(4, latitude - lenght/2);
				pStatement.setDouble(5, longitude + lenght/2);
				pStatement.setDouble(6, longitude - lenght/2);		
			}*/
        
        	/*result = pStatement.executeQuery();

			while(result.next()){
				
				String toPass[] = { Integer.toString(result.getInt("clump_id")),
									Double.toString(result.getDouble("g_lat")),
									Double.toString(result.getDouble("g_lon")),
									Double.toString(result.getDouble("distance"))
									};
				data.add(toPass);
			}*/
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(data, new DistanceComparator());

		return data;
    }
	
	 public static void main(String[] args) {
		ClumpDao clumpD = new ClumpDao();
		Vector<String[]> results = new Vector<String[]>();
		results = clumpD.showClumpsInArea(0.0,42.7,20.0, false);
		for(String[] v : results) {
			for(String k : v)
				System.out.println(k);
		}
	}
}

