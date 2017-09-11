package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SourceDao {

	
	
	
	
	
	 public Vector<String[]> findSourcesInMap (String map, Float band){ 
	    	Vector<String[]> v = null;
	    	Connection connection = null;
	        ResultSet result = null;
			v= new Vector<String[]>();

	        String query = "SELECT DISTINCT s.source_id, fs.value, fs.error FROM source s join flux_source fs on" +
	        				"s.source_id = fs.source_id join map m on m.map_id = s.map_id WHERE m.map_name = '"+map+"' ";
	        if(band != null)
	        		 query = query + "AND sf.band_resolution = ?"; //da inserire, è un double/float
	        			        
	        	query = query + ";";
	        	
	    	try { //TODO check || NB: è per il requisito 5
			
	    		DataSource d = new DataSource();
	        	connection = d.getConnection();
	        	PreparedStatement pStatement = connection.prepareStatement(query);
	        	Double dband = Double.parseDouble(Float.toString(band));
	        	pStatement.setDouble(1,dband);
	        	result = pStatement.executeQuery(query); 
	    		}
					catch (SQLException se) {
						//TODO
					}
	    		catch(Exception e) {
	    				System.out.println("ToolDao.java: catch after try");
	      		} 
			
					String[] s = new String[5];
			    	try {
			    		while(result.next()){
			    			s[0] = result.getString("source_mapcode"); 
			    			s[1] = Double.toString(result.getDouble("value")); //si fa col toString?
			    			s[2] = Double.toString(result.getDouble("error"));
			    			s[3] = Double.toString(result.getDouble("latitude"));
			    			s[4] = Double.toString(result.getDouble("longitude"));
			    			v.add(s);
			    		}
			    	}
			    	catch (SQLException e1) {
			    		e1.printStackTrace();
			    	}
			 	 return v;
	    	
		}
	 
	 public Vector<String[]> showSourcesInArea(Double latitude,Double longitude, Double lenght, boolean isCircle){
			
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
	    }
	 
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
}
