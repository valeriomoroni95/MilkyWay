package controller;

import java.util.Vector;

import boundary.ShowClumpInfoBean;
import boundary.ShowObjectsInAreaBean;
import boundary.ShowMassiveStarBean;
import dao.ClumpDao;
import entity.Clump;


public class ClumpController {
	
	private static ClumpController instance;
	
	  private ClumpController(ShowObjectsInAreaBean sciab){
		  
	  }
	  
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
	  
	  public static synchronized ClumpController getInstance(ShowObjectsInAreaBean sciab) {
		    if(instance == null)
		      instance = new ClumpController(sciab);
		    return instance;
	  }
	  
	  public Vector<Clump> getMassiveStars() {
		 
		  Vector<Clump> clump = new Vector<Clump>();           
		  ClumpDao clumpDao = new ClumpDao();
		  clump = clumpDao.findMassiveStar();
		    
		  return clump;
	  }
	  
	  public Vector<String[]> getClumpInfo(int clumpId) {
		  
		  Vector<String[]> clump = new Vector<String[]>();           
		  ClumpDao clumpDao = new ClumpDao();
		  clump = clumpDao.showClumpInfo(clumpId);
		    
		  return clump;
	  }
	 
	  public Vector<String[]> getClumpsInArea(Double latitude,Double longitude, Double lenght, boolean isCircle) {
		  
		  Vector<String[]> clumps = new Vector<String[]>();           
		  ClumpDao clumpDao = new ClumpDao();
		  clumps = clumpDao.showClumpsInArea(latitude, longitude, lenght, isCircle);
		    
		  return clumps;
	  }

}
