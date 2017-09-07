package dao;

import java.sql.Connection;
import entity.Clump;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public Vector<String[]> findClumpInMap (String map,Float band){ 
    	Vector<String[]> v = null;
    	Connection connection = null;
        ResultSet result = null;
		v = new Vector<String[]>();

        String query = "SELECT DISTINCT c.clump_id, fc.value, fc.error FROM clump c join flux_clump fc on" +
        				"c.clump_id = fc.clump_id join map m on c.map_id = m.map_id WHERE m.map_name = '" + map + "' "; 
        if(band != null)
        		 query = query + "AND fc.band_resolution = ?";
        			        
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
					se.printStackTrace();				}
    		catch(Exception e) {
    				e.printStackTrace();
      		} 
		
				String[] s = new String[5];
		    	try {
		    		while(result.next()){
		    			s[0] = Integer.toString(result.getInt("clump_id"));
		    			s[1] = Double.toString(result.getDouble("value")); 
		    			s[2] = Double.toString(result.getDouble("error"));
		    			s[3] = Double.toString(result.getDouble("g_lat"));
		    			s[4] = Double.toString(result.getDouble("g_lon"));

		    			//TODO salvare anche posizione spaziale? 
		    			v.add(s);
		    		}
		    	}
		    	catch (SQLException e1) {
		    		e1.printStackTrace();
		    	}
		 	 return v;
	}
	
	
	/* VA NEL SOURCEDAO!
	 *  
	 * public Vector<String[]> findSourceInMap (String map,Float band){ 
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
					//errore etc
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
		    			s[3] = Double.toString(result.getDouble("g_lat"));
		    			s[4] = Double.toString(result.getDouble("g_lon"));
			    	//TODO salvare anche posizione spaziale? 
		    			v.add(s);
		    		}
		    	}
		    	catch (SQLException e1) {
		    		e1.printStackTrace();
		    	}
		 	 return v;
    	
	}*/	
}
