package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import entity.ClumpMass;

public class MassDao {

	
	
	public ClumpMass saveClumpMass(ResultSet rs){
    	ClumpMass mc =null;
    	try {
			int x =rs.getInt("clump_id");
	    	double m = Double.parseDouble(rs.getString("totalMass"));
	    	mc = new ClumpMass(x,m);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return mc; 	
    	
    }
	

    public Vector<ClumpMass> loadMasses(int clumpId,double latitude,double longitude,double flux,double band) throws ClassNotFoundException{
    	Vector<ClumpMass> masses = null;
    	Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try{
			String view = "CREATE OR REPLACE VIEW numSorgClump AS "+                    //la vista se crea così ma noi vogliamo la misurazione
					  "SELECT c.id_clump,c.ktemp,f.valore,count(*) as numSorg "+        //del flusso a risoluzione 350
					  "FROM sorgente s join clump c on s.id_clump=c.id_clump "+
					  "join flusso_clump f on s.id_clump=f.id_clump "+
					  "WHERE f.risoluzione_banda=350 "+
					  "GROUP BY c.id_clump,c.ktemp,f.valore";
			DataSource d = new DataSource();
			connection = d.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(view);
			String query = "SELECT id_clump,(numsorg*(0.053*valore*100)*((EXP(41.14/ktemp) - 1))) "+ //al posto di numsorg avremo la vista di cui sopra
						"AS MassaTot FROM numsorgclump";
			rs = statement.executeQuery(query);
			if(!rs.next())
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
		String query;
		String[] statClump = new String[4];
		try{
			String view = "CREATE OR REPLACE VIEW numSorgClump AS "+                  //questa view non deve essere così, ma come quella fatta  
					  "SELECT c.id_clump,c.ktemp,f.valore,count(*) as numSorg "+	 //nel metodo prima
					  "FROM sorgente s join clump c on s.id_clump=c.id_clump "+
					  "join flusso_clump f on s.id_clump=f.id_clump "+
					  "WHERE f.risoluzione_banda=350 "+
					  "GROUP BY c.id_clump,c.ktemp,f.valore";
			DataSource d = new DataSource();
			connection = d.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(view);                                            //questa era la view per vedere le masse
			String view2 = "CREATE OR REPLACE VIEW MassaClump AS SELECT "+
					    "id_clump,(numsorg*(0.053*valore*100)*((EXP(41.14/ktemp) - 1))) "+
						"AS MassaTot FROM numsorgclump;";
			statement.executeUpdate(view2);			
			/*SELECT AVG(massatot) AS MEDIA,STDDEV(massatot) AS DEV,MEDIAN(CAST(massatot AS Integer)) AS MEDIANA,    
	(SELECT  MEDIAN(CAST (ABS(MassaTot - med) AS Integer))															 
					FROM    (                                                                                                                
					SELECT  MassaTot, MEDIAN(CAST(massatot AS Integer)) OVER() AS med                       
					FROM    MassaClump
					) AS c AS DEVIAZIONE_MEDIA_ASSOLUTA)                                                       
FROM MassaClump*/
			String assDev = "SELECT MEDIAN(CAST (ABS(MassaTot - med) AS Integer)) "+
						 "FROM    ( "+
					
					     "( SELECT  MassaTot, MEDIAN(CAST(MassaTot AS Integer)) OVER() AS med "+
					      "FROM    MassaClump "+
					      ") AS c";
			query = "SELECT AVG(MassaTot) AS MEDIA,STDDEV(MassaTot) AS DEVIAZIONE_STANDARD,MEDIAN(CAST(MassaTot as Integer)) AS "+
					 "MEDIANA,(SELECT MEDIAN(CAST (ABS(MassaTot - med) AS Integer)) FROM "+
					 "( SELECT  MassaTot, MEDIAN(CAST(MassaTot AS Integer)) OVER() AS med FROM    MassaClump ) AS c) AS "+
					 "DEVIAZIONE_MEDIA_ASSOLUTA FROM MassaClump;";

			System.out.println("print query: " + query);
			rs = statement.executeQuery(query);											//questa me sa che era la roba che me diceva claudio
			if(!rs.next())
				return null;
			statClump[0] = rs.getString("MEDIA");
			statClump[1] = rs.getString("DEVIAZIONE_STANDARD");
			statClump[2] = rs.getString("MEDIANA");
			statClump[3] = rs.getString("DEVIAZIONE_MEDIA_ASSOLUTA");
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
    	return statClump;	
			
					
    
    
		
		
		
    }


}
