package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Vector;

import entity.Agency;
import entity.Satellite;

public class SatelliteDao {
	
	public Vector<Satellite> showSatellites() throws SQLException {
        
        Connection connection  = null;
        Statement statement = null;
        Vector<Satellite> satellites = new Vector<Satellite>();    
        ResultSet result = null;
         
        final String query = "SELECT * FROM \"satellite\" s join \"tool_satellite\" ts on s.name = ts.satellite_name "+
        			"join tool t on ts.tool_name = t.tool_name join agency_satellite ag_sat on ag_sat.satellite_name ="+
        			" s.name join agency a on ag_sat.agency_id = a.id order by s.name, ts.tool_name;"; 
            
        System.out.println("SatelliteDao.java: query " + query);
        
        try {
        	
    			System.out.println("SatelliteDao.java: try start");

        		DataSource d = new DataSource();
        		
        		System.out.println("SatelliteDao.java: dataSource " + d);
        		
        		connection = d.getConnection();
        		System.out.println("SatelliteDao.java: connection " + connection);
        		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        		System.out.println("SatelliteDao.java: statement " + statement);

        		result = statement.executeQuery(query);
            
        		System.out.println("SatelliteDao.java: result " + result);
        		
            String currSatelliteName = "\n";
            String start = "\n";
            String end = null;
            String lastTool = "\n";
            Vector<String> tools = new Vector<String>();
            Vector<Agency> agencies = new Vector<Agency>();
            
            Satellite s = null;
            
            if (!result.first()) {
            	
        			System.out.println("SatelliteDao.java: if !result.first -> null");

        			return null;
            }
             
            while(result.next()) {
            	
        				if(currSatelliteName != result.getString("name") && currSatelliteName != "\n") {	
            		            				
            				s = new Satellite(currSatelliteName, start, end, tools);
            				
                    		System.out.println("SatelliteDao.java: new satellite " + s);

            				satellites.add(s);
            				
            				currSatelliteName ="\n";
            				start = "\n";
            				end = null;
            				tools.clear();
            				agencies.clear();
            			
                    		System.out.println("SatelliteDao.java: satellite result list " + satellites);
	
            			}
        				
            				currSatelliteName = result.getString("name");
            				start = result.getString("satellite_start");
            				end = result.getString("satellite_end");
            				if(end == "")
            					end = null;
            				if(lastTool != result.getString("tool_name") && lastTool != "\n") {
            					lastTool = result.getString("tool_name");
            					tools.add(lastTool);
            				}
            				Agency agency = new Agency();
            				agency.setAgencyId(result.getInt("id"));
            				agency.setAgencyName(result.getString("name"));
            				agencies.add(agency);
            	}
                    
        } catch(Exception e) {
        	
        		e.printStackTrace();
        		
        } finally {
        	
        	  	System.out.println("SatelliteDao.java: finally");
        	  	
        	  	if(result != null)
        	  		result.close();
        	  	
        	  	if(statement != null)
        	  		statement.close();
        	  	
        	  	if(connection != null)
        	  		connection.close();
        	}
        
        return satellites;
        
	}
	
	public static boolean isSatellitePresent(String name, String start, String end, Vector<String> tools, Vector<String> agencies){
		
		Connection connection = null;
        Statement statement = null;
		PreparedStatement pstatement = null;
        ResultSet result = null;
        
        final String query = "SELECT \"name\" FROM \"satellite\" WHERE \"name\" = '"+name+"';";
		
        try{
        	
        		DataSource d = new DataSource();
            connection = d.getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(query);
            
            if (result == null) {
            	
                return false;
                
            }
            
            if(result.first()) {
            	
            		return false;
            		
            }else{
            	
                String insert = "INSERT INTO \"satellite\" VALUES ('"+start+"','"+end+"','"+name+"');";
                statement.executeUpdate(insert);
                
                String insert2 = "INSERT INTO \"tool_satellite\"(tool_name, satellite_name) VALUES (?,?);"; 
                pstatement = connection.prepareStatement(insert2);
				
            		for(String tool : tools) {
            			pstatement.setString(1, tool);
            			pstatement.setString(2, name);
            			pstatement.executeUpdate();
            		}
            	
            		String insert3 = "INSERT INTO \"agency_satellite\"(agency_id, satellite_name) VALUES (?,?);"; 
            		pstatement = connection.prepareStatement(insert3);
            		
            		for(String agency : agencies) { 
            			pstatement.setString(2, name);
            			pstatement.setInt(1, Integer.parseInt(agency));
            			pstatement.executeUpdate();
            		}
            		
           		result.close();
           		statement.close();
           		connection.close();
            
            }
            
        }catch(Exception e){
        		e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            	se2.printStackTrace();
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

