package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SourceDao {

	
	
	
	
	
	 public Vector<String[]> findSourceInMap (String map, Float band){ 
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
	 
}
