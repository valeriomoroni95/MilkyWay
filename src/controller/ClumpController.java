package controller;

import java.util.Vector;
import boundary.ShowMassiveStarBean;
import dao.ClumpDao;
import entity.Clump;


public class ClumpController {
	
	private static ClumpController instance;
	  
	  private ClumpController(ShowMassiveStarBean msb) {
	    
	  }
	  
	  private ClumpController() {
		  
	  }
	  public static synchronized ClumpController getInstance(ShowMassiveStarBean msb) {
	    if(instance == null)
	      instance = new ClumpController(msb);
	    return instance;
	  }
	  
	  /*public Vector<Clump> getClumpList() throws SQLException{  //TODO va creato il metodo showClumps nel clump dao
		    Vector<Clump> clump = new Vector<Clump>();            
		        
		    ClumpDao clumpDao = new ClumpDao();
		    
		    clump = clumpDao.showClumps();
		    
			return clump;
			
		  }*/
	  
	  public Vector<Clump> getMassiveStars() {
		 
		  Vector<Clump> clump = new Vector<Clump>();           
		  ClumpDao clumpDao = new ClumpDao();
		  clump = clumpDao.findMassiveStar();
		    
		  return clump;
	  }
	  
	 

}
