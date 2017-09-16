package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import entity.ClumpMass;

public class MassDao {

	
	
	public ClumpMass saveClumpMass(ResultSet rs){
    	ClumpMass mc = null;
    	try {
			int x =rs.getInt("clump_id");
	    	double m = Double.parseDouble(rs.getString("mass"));
	    	mc = new ClumpMass(x,m);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return mc; 	
    	
    }
	

    public Vector<ClumpMass> loadMasses() throws ClassNotFoundException{
    	Vector<ClumpMass> masses = null;
    	Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try{
			String view = "CREATE OR REPLACE VIEW s350 AS "+ 
					  "SELECT c.clump_id, fc.value, c.k_temp "+ 
					  "FROM clump c join flux_clump fc on fc.clump_id=c.clump_id " +
					  "WHERE fc.band_resolution=350.0 and c.k_temp <> 0.0 "+
					  "GROUP BY c.clump_id,c.k_temp,fc.value";
			DataSource d = new DataSource();
			connection = d.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(view);
			String query = "SELECT clump_id,(0.053*value*100)*((EXP(41.14/k_temp) - 1)) "+ 
						   "AS mass FROM s350;";
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = statement.executeQuery(query);
			if(!rs.first())
				return null;
			masses = new Vector<ClumpMass>();
			for(;;){
				 masses.add(saveClumpMass(rs));
				 if(!rs.next())
					 break;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	return masses;	
	}
    
   
     public String[] statsMass() throws ClassNotFoundException{
    	Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		String[] statClump = new String[4];
		try{
			String view = "CREATE OR REPLACE VIEW s350 AS "+                   
					  "SELECT c.clump_id, fc.value, c.k_temp "+        
					  "FROM clump c join flux_clump fc on fc.clump_id=c.clump_id " +
					  "WHERE fc.band_resolution=350.0 and c.k_temp <> 0.0 "+
					  "GROUP BY c.clump_id,c.k_temp,fc.value";
			System.out.println("Ho creato s350");
			DataSource d = new DataSource();
			connection = d.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(view);
			
			String view2 = "CREATE OR REPLACE VIEW mass_data AS SELECT clump_id,  (0.053*value*100)*((EXP(41.14/k_temp) - 1)) "+ 
					   	"AS mass FROM s350;";
			statement.executeUpdate(view2);
			System.out.println("Ho eseguito la query view2");
			
			String medianQuery = "SELECT CASE WHEN c % 2 = 0 AND c > 1 THEN (a[1]+a[2])/2 ELSE a[1] END "+
					"FROM (SELECT ARRAY(SELECT mass FROM mass_data ORDER BY mass OFFSET (c-1)/2 LIMIT 2) AS a, c "+
					   "FROM (SELECT count(*) AS c FROM mass_data where mass is not null) AS count OFFSET 0 ) AS midrows;";

			System.out.println("print query: " + medianQuery);
			rs = statement.executeQuery(medianQuery);											
			if(!rs.next())
				return null;
			statClump[0] = rs.getString("a"); //MEDIANA!
			System.out.println(statClump[0]);
			
			String avgQuery = "SELECT AVG(mass)as average, STDDEV(mass) as std_deviation from mass_data;";
			rs = statement.executeQuery(avgQuery);											
			if(!rs.next())
				return null;
			statClump[1] = rs.getString("average");
			System.out.println(statClump[1]);
			statClump[2] = rs.getString("std_deviation");
			System.out.println(statClump[2]);
			statClump[3] = Double.toString(Double.parseDouble(statClump[2])*0.6745);
			System.out.println(statClump[3]);

		
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
			}
    	return statClump;	
			
     }


}
