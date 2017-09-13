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
         
        final String query = "SELECT s.name as satellitename, s.satellite_start as satellitestart, s.satellite_end"+
        " as satelliteend, ts.tool_name as toolname, a.name as agencyname, a.id as agencyid FROM \"satellite\" s join \"tool_satellite\" ts on s.name = ts.satellite_name "+
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
            String temp = "\n";
            Satellite s = null;
            String tempTool = "\n";
            String i = "i";
            Agency agency = new Agency();
            int aTemp = -1;
            /*if (!result.first()) {
            	
        			System.out.println("SatelliteDao.java: if !result.first -> null");

        			return null;
            }*/
             
            while(result.next()) {
            			
            			System.out.println(i);
            			temp = result.getString("satellitename");
            			if(!currSatelliteName.equals(temp) && !currSatelliteName.equals("\n")) {	    				
            				s = new Satellite(currSatelliteName, start, end, tools, agencies);
                    		System.out.println("SatelliteDao.java: new satellite \n" + s.toString());
            				satellites.add(s);
            				
            				currSatelliteName ="\n";
            				start = "\n";
            				end = null;
            				lastTool = "\n";
            				tools = new Vector<String>();
            				agencies = new Vector<Agency>();
                    		//System.out.println("SatelliteDao.java: satellite result list " + satellites);
	
            			}
        				
            			currSatelliteName = temp;
            			start = result.getString("satellitestart");
            			end = result.getString("satelliteend");
            			/*if(end == "")
           					end = null;*/
            			tempTool = result.getString("toolname");
           				//System.out.println(lastTool + " " + tempTool);
            			if(!lastTool.equals(tempTool)) {
           					lastTool = tempTool;
           					tools.add(lastTool);
           					//System.out.println(currSatelliteName +" "+ lastTool + " " + tools);
           				}
            			aTemp =	result.getInt("agencyid");
            			boolean check = true;
            			for(Agency ag : agencies) {
                			if(ag.getAgencyId()==aTemp) {
                				check = false;
                				break;
                			}			
            			}
            				if(check) {
            					agency = new Agency();
           						agency.setAgencyId(aTemp);
           						agency.setAgencyName(result.getString("agencyname"));
           						agencies.add(agency);
            				}
           				i = i + "i";
        				
            	}
			s = new Satellite(currSatelliteName, start, end, tools, agencies);
    		System.out.println("SatelliteDao.java: new satellite \n" + s.toString());
    		satellites.add(s);

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
            
            if(result.first())
            		return false;
            		
            else{
            	
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

	/*public static void main(String args[]) throws SQLException {
		Vector<Satellite> v = new Vector<Satellite>();
		SatelliteDao c = new SatelliteDao();
		v = c.showSatellites();
		for(Satellite sat : v) {
			System.out.println("satelliti: " + sat.toString());
		}
	}*/
}