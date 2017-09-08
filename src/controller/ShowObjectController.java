package controller;

import java.sql.SQLException;
import java.util.Vector;

import boundary.ShowObjectBean;
import dao.ClumpDao;
import dao.MapDao;
import entity.Clump;

public class ShowObjectController {
	
	private static ShowObjectController instance;
	private Clump c; 
	private ShowObjectController(ShowObjectBean ob) {
    
	}
  
	private ShowObjectController() {
	  
	}
  
	public static synchronized ShowObjectController getInstance(ShowObjectBean ob) {
		if(instance == null)
			instance = new ShowObjectController(ob);
    return instance;
	}

	public Vector<String> getMapNames() throws SQLException{ //prende dal Dao lista dei nomi delle mappe correnti
		
		Vector<String> mapNames = new Vector<String>();
        
		MapDao mapDao = new MapDao();
    
		mapNames = mapDao.showMapNames();
    
		return mapNames;
	
   }
	
	public Vector<String[]> getClumps() {
		
		Vector<String[]> clumps = new Vector<String[]>();
		ClumpDao clumpDao = new ClumpDao();
		clumps = clumpDao.showClumpInfo(this.c.getClump_id());  //Il clump passato è quello dell'istanza corrente (dovrebbe essere giusto, controllare)
		return clumps;
		
	}

	
	
}
