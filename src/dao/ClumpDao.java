package dao;

import java.sql.Connection;
import entity.Clump;
import java.sql.PreparedStatement;

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
}
