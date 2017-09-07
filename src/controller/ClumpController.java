package controller;

import java.sql.SQLException;
import java.util.Vector;

import boundary.ClumpBean;
import dao.ClumpDao;
import entity.Clump;


public class ClumpController {
	
	private static ClumpController instance;
	  
	  private ClumpController(ClumpBean cb) {
	    
	  }
	  
	  private ClumpController() {
		  
	  }
	  public static synchronized ClumpController getInstance(ClumpBean cb) {
	    if(instance == null)
	      instance = new ClumpController(cb);
	    return instance;
	  }
	  
	  public Vector<Clump> getClumpList() throws SQLException{
		    Vector<Clump> clump = new Vector<Clump>();
		        
		    ClumpDao clumpDao = new ClumpDao();
		    
		    clump = clumpDao.showClumps();
		    
			return clump;
			
		  }

}
