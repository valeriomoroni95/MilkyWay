package controller;

import java.sql.SQLException;
import java.util.Vector;

import boundary.ShowObjectBean;
import dao.MapDao;

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

	public Vector<String> getMapNames() throws SQLException{ //prende dal Dao lista dei nomi delle mappe correnti
		
		Vector<String> mapNames = new Vector<String>();
        
		MapDao mapDao = new MapDao();
    
		mapNames = mapDao.showMapNames();
    
		return mapNames;
	
   }

	
	
}
