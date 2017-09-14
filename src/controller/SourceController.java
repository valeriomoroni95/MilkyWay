package controller;


import java.util.Vector;

import boundary.ShowObjectsInAreaBean;
import dao.ClumpDao;
import dao.SourceDao;
import entity.Source;


	public class SourceController {
		
		private static SourceController instance;
		
		  private SourceController(ShowObjectsInAreaBean sciab){
			  
		  }
		  
		  
		  
		  private SourceController() {
			  
		  }
		  
		  
		  public static synchronized SourceController getInstance(ShowObjectsInAreaBean sciab) {
			    if(instance == null)
			      instance = new SourceController(sciab);
			    return instance;
		  }
		  
		  public Vector<String[]> getSourcesInArea(Double latitude,Double longitude, Double lenght, boolean isCircle) {
			  
			  Vector<String[]> sources = new Vector<String[]>();           
			  SourceDao sourceDao = new SourceDao();
			  sources = sourceDao.showSourcesInArea(latitude, longitude, lenght, isCircle);
			    
			  return sources;
		  }
}