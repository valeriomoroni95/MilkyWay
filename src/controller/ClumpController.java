package controller;

import java.util.Vector;

import boundary.ShowClumpInfoBean;
import boundary.ShowMassiveStarBean;
import dao.ClumpDao;
import entity.Clump;


public class ClumpController {
	
	private static ClumpController instance;
	  
	  private ClumpController(ShowMassiveStarBean msb) {
	    
	  }
	  
	  private ClumpController() {
		  
	  }
	  
	  private ClumpController(ShowClumpInfoBean scib){
		  
	  }
	  
	  public static synchronized ClumpController getInstance(ShowMassiveStarBean msb) {
	    if(instance == null)
	      instance = new ClumpController(msb);
	    return instance;
	  }
	  
	  
	  public static synchronized ClumpController getInstance(ShowClumpInfoBean scib) {
		    if(instance == null)
		      instance = new ClumpController(scib);
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
	  
	  public Vector<String[]> getClumpInfo(int clumpId) {
		  
		  Vector<String[]> clumps = new Vector<String[]>();           
		  ClumpDao clumpDao = new ClumpDao();
		  clumps = clumpDao.showClumpInfo(clumpId);
		    
		  return clumps;
	  }
	 

}
