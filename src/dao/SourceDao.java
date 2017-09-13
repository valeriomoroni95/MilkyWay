package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Vector;
import entity.DistanceComparator;

public class SourceDao {

	
	 public Vector<String[]> findSourcesInMap (String map, Double band){ 
	    	Vector<String[]> v = null;
	    	Connection connection = null;
	        ResultSet result = null;
			v= new Vector<String[]>();
			PreparedStatement pStatement = null;
			Statement statement = null;
	        String query = "SELECT distinct s.source_id, fs.value, fs.error, s.source_mapcode, s.latitude, s.longitude, fs.band_resolution FROM source s join flux_source fs on" +
	        				" s.source_id = fs.source_id join map m on m.map_id = s.map_id WHERE m.name = '"+map+"' ";
	      
	        if(band != 0.0)
	        		 query = query + "AND sf.band_resolution = ?"; //da inserire, è un double
	        			        
	        	query = query + "order by s.source_id;";
	        	
	    	try { //TODO check || NB: è per il requisito 5
			
	    		DataSource d = new DataSource();
	        	connection = d.getConnection();
	        	if(band!=0.0) {
		        	pStatement = connection.prepareStatement(query);
	        		pStatement.setDouble(1,band);
	        		result = pStatement.executeQuery(); 
	        	}
	        	else {
	        		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        		System.out.println("vivo prima execute");
	        		result = statement.executeQuery(query);
	        		System.out.println("vivo dopo execute");

	        	}
	        }
					catch (SQLException se) {
						se.printStackTrace();
						}
	    		catch(Exception e) {
	    				System.out.println("ToolDao.java: catch after try");
	      		} 
			
					String[] s = new String[6];
			    	try {
		    			String currSource = "";
		    			String tempS = null;

		    			while(result.next()){

		    				if (band!=0.0) {
		    					s = new String[6];

			    				s[0] = result.getString("source_mapcode"); 
			    				s[1] = Double.toString(result.getDouble("latitude"));
			    				s[2] = Double.toString(result.getDouble("longitude"));
			    				s[3] = Double.toString(band);
			    				s[4] = Double.toString(result.getDouble("value")); //si fa col toString?
			    				s[5] = Double.toString(result.getDouble("error"));
			    				v.add(s);
			    			}
			    		
			    			else {
				    				
				    				tempS = result.getString("source_mapcode");
				    				if(!currSource.equals(tempS)) {
				    					s = new String[6];
				    					s[0] = tempS; 
				    					s[1] = Double.toString(result.getDouble("latitude"));
				    					s[2] = Double.toString(result.getDouble("longitude"));
				    					s[3] = Double.toString(result.getDouble("band_resolution"));
					    				s[4] = Double.toString(result.getDouble("value")); //si fa col toString?
					    				s[5] = Double.toString(result.getDouble("error"));
					    				v.add(s);
					    				currSource = tempS;
					    				
				    				}
				    				else {
				    					s = new String[6];

				    					s[0] = " ";
				    					s[1] = " ";
				    					s[2] = " ";
				    					s[3] = Double.toString(result.getDouble("band_resolution"));
					    				s[4] = Double.toString(result.getDouble("value")); //si fa col toString?
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
	 
	 public Vector<String[]> showSourcesInArea(Double latitude,Double longitude, Double lenght, boolean isCircle){
			
			//permette di cercare i clump che si trovano in un cerchio o un quadrato centrato in latitude, longitude
			// e con raggio/cateto di lunghezza lenght. Se cerco in un cerchio isCircle = true, rettangolo false
	 		// l'array di stringhe ritornato nel vettore ha il formato [id, latitudine, longitudine, distanza]

			Vector<String[]> data = new Vector<String[]>();
			String query, condition;
	    	Connection connection = null;
			PreparedStatement pStatement = null;
			ResultSet result;		
			
			try {
				
				query = "SELECT s.source_mapcode, s.latitude, s.longitude "  + 
						"FROM source s;";
				
				DataSource d = new DataSource();
	            connection = d.getConnection();
	        	pStatement = connection.prepareStatement(query);
	        	result = pStatement.executeQuery();
	        	String mapCode;
	        	Double lat, lon, distance;
	        	while(result.next()) {
	        		
	        		mapCode = result.getString("source_mapcode");
	        		lat = result.getDouble("latitude");
	        		lon = result.getDouble("longitude");
	        		
	        		if(isCircle) {
	        			distance = Math.sqrt(Math.pow(latitude - lat, 2)+Math.pow(longitude - lon,2));
	        			if(distance < lenght) {
	        				String toPass[] = {	mapCode,
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
	        					distance = Math.sqrt(Math.pow(latitude-lat, 2)+Math.pow(longitude-lon,2));
	        					String toPass[] = { mapCode,
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
	/* public Vector<String[]> showSourcesInArea(Double latitude,Double longitude, Double lenght, boolean isCircle){
			
			// permette di cercare le sorgenti che si trovano in un cerchio o un quadrato centrato in latitude, longitude
			// e con raggio/cateto di lunghezza lenght. Se cerco in un cerchio isCircle = true, rettangolo false
		 	// l'array di stringhe ritornato nel vettore ha il formato [id, latitudine, longitudine, distanza]
		 	// castate a String (vanno solo stampate). L'id stampato è quello contenuto nei file
			Vector<String[]> data = new Vector<String[]>();
			String query, condition;
	    	Connection connection = null;
			PreparedStatement pStatement = null;
			ResultSet result;		
			
			try {
				
				query = "SELECT s.source_mapcode, s.latitude, s.longitude, SQRT(POW((s.latitude - ?),2)+" + 
						"POW((s.longitude - ?),2)) as distance FROM  flux_source f JOIN "  + 
						"source s on s.source_id = f.source_id ";
				
				DataSource d = new DataSource();
	            connection = d.getConnection();
	        	
	        	if(isCircle) {
					condition = "WHERE distance < ? order by distance;";
		        	query = query + condition; 
					pStatement = connection.prepareStatement(query);

					pStatement.setDouble(1, latitude);
					pStatement.setDouble(2, longitude);
					pStatement.setDouble(3, lenght);
	        	}
	        	
				else {
					
					condition = "WHERE g_lat < ? AND g_lat > ? AND g_lon < ? AND g_lon > ?" +
								"order by distance;";
					query = query + condition; 
					pStatement = connection.prepareStatement(query);
					pStatement.setDouble(1, latitude);
					pStatement.setDouble(2, longitude);
					pStatement.setDouble(3, latitude + lenght/2);
					pStatement.setDouble(4, latitude - lenght/2);
					pStatement.setDouble(5, longitude + lenght/2);
					pStatement.setDouble(6, longitude - lenght/2);		
				
				}
	        	
				query = query + condition;
	        	
	        	result = pStatement.executeQuery(query);

				while(result.next()){
					
					String toPass[] = { result.getString("source_mapcode"),
										Double.toString(result.getDouble("latitude")),
										Double.toString(result.getDouble("longitude")),
										Double.toString(result.getDouble("distance"))
										};
					data.add(toPass);
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
	    } */
	 
	 public boolean isPresent(String source_mapcode) {
			Connection connection = null;
	        PreparedStatement statement = null;
	           	
	        ResultSet result = null;
	        	
	        
	        try {
	       	 	
	        	System.out.println("SourceDao: isPresent.java: sto dopo il try");
	        	final String query = "SELECT \"source_mapcode\" FROM \"source\" WHERE \"source_mapcode\" = ?;";
	        	DataSource d = new DataSource();
	        	System.out.println("SourceDao: isPresent.java: sopravvissuto al Datasource");

	            connection = d.getConnection();
	            
	            PreparedStatement pStatement = connection.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	            pStatement.setString(1, source_mapcode);
	            result = pStatement.executeQuery();
	            System.out.println("SourceDao: isPresent.java: ho eseguito la query");

	            
	            
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
	 
	/* public static void main(String[] args) {
			SourceDao sourceD = new SourceDao();
			Vector<String[]> results = new Vector<String[]>();
			results = sourceD.showSourcesInArea(0.0,42.7,2.0, false);
			for(String[] v : results) {
				for(String k : v)
					System.out.println(k);
			}
	 
	 }*/

/*Trovare tra le sorgenti all’interno della mappa MIPSGAL, 
 * tutte quelle che sono all’interno di uno specifico clump e per una specifica banda. 
 * Un oggetto si dice che appartiene a un clump se la distanza tra la posizione della sorgente  
 * e quella del clump è minore dell’asse maggiore dell’ellisse per la banda selezionata. */

	 public Vector<String[]> findSourcesInClump(int clumpId, Double bandRes){
		 
		 Vector<String[]> sources = new Vector<String[]>();
		 String query = "SELECT c.g_lat, c.g_lon, e.x_axis, e.y_axis FROM clump c join ellipse e on c.clump_id " +
				 		"=e.clump_id AND e.band_resolution = ?";
		 String query2 = "SELECT s.source_mapcode, s.latitude, s.longitude FROM source s join map m on s.map_id " +
				 "= m.map_id where m.name = 'MIPS-GAL' OR m.name = 'MIPSGAL' OR m.name = 'mipsgal' OR m.name = 'mips-gal'"+
				 "OR m.name = 'Mipsgal';";
		 Double cLatitude = 0.0;
		 Double cLongitude= 0.0;
		 Double x = 0.0;
		 Double y= 0.0;
		 Connection connection = null;
	     ResultSet result = null;
		 PreparedStatement pStatement = null;
		 Connection connection2 = null;
	     ResultSet result2 = null;
		 Statement statement2 = null;
		 DataSource d = new DataSource();
		 String sourceCode;
		 Double sLatitude;
		 Double sLongitude;
		 String[] toPass = new String[3];

		 try {
			 
	         connection = d.getConnection();
	         pStatement = connection.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
     		 pStatement.setDouble(1, bandRes);
	         result = pStatement.executeQuery();
     		 
     		 if(!result.first()){
     			 return null;
     		 }
     		 if(result.next()) {
     			 cLatitude = result.getDouble("g_lat");
     			 cLongitude = result.getDouble("g_lon");
     			 x = result.getDouble("x_axis");
     			 y = result.getDouble("y_axis");
     		 }
     		 connection2 = d.getConnection();
			 connection2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
     		 result2 = statement2.executeQuery(query2);
     		 if(!result2.first())
     			 return null;
     		 
     		 while(result2.next()) {
     			 sourceCode = result.getString("source_mapcode");
     			 sLatitude = result.getDouble("latitude");
     			 sLongitude = result.getDouble("longitude");
     			 
     			 if(Math.sqrt(Math.pow(sLatitude - cLatitude, 2)+
     				Math.pow(sLongitude - cLongitude, 2)) < Math.max(2*x, 2*y)) {
     			     toPass[0] = sourceCode;
     			     toPass[1] = Double.toString(sLatitude);
     			     toPass[2] = Double.toString(sLongitude);
     				 sources.add(toPass); 
     			 }	 
     		 }
		 }catch(SQLException se) {
			 se.printStackTrace();
		 } catch(Exception e) {
			 e.printStackTrace();
		 }

		 return sources;
	 }

}


