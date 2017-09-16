package controller;

import java.sql.SQLException;
import java.util.Vector;
import boundary.ShowObjectBean;
import dao.ClumpDao;
import dao.MapDao;
import dao.SourceDao;

public class ShowObjectController {
	
	private static ShowObjectController instance;

	private ShowObjectController(ShowObjectBean ob) {
    
	}
  
	private ShowObjectController() {
	  
	}
  
	public static synchronized ShowObjectController getInstance(ShowObjectBean ob) {
		if(instance == null)
			instance = new ShowObjectController(ob);
    return instance;
	}

	public Vector<String> getMapNames() throws SQLException{ 
		
		Vector<String> mapNames = new Vector<String>();
        
		MapDao mapDao = new MapDao();
    
		mapNames = mapDao.showMapNames();
    
		return mapNames;
	
   }
	
	public Vector<String[]> getClumps(String mapName, Double band) {
		
		Vector<String[]> clumps = new Vector<String[]>();
		ClumpDao clumpDao = new ClumpDao();
		clumps = clumpDao.findClumpsInMap(mapName, band);
		return clumps;
		
	}
	
	public Vector<String[]> getSources(String mapName, Double band){
		
		Vector<String[]> sources = new Vector<String[]>();
		SourceDao sourceDao = new SourceDao();
		sources = sourceDao.findSourcesInMap(mapName, band);
		return sources;
		
	}

	
	
}
