package controller;


import java.util.Vector;

import boundary.ShowObjectsInAreaBean;
import boundary.ShowSourcesInClumpBean;
import boundary.YoungSourceObjectBean;
import dao.AgencyDao;
import dao.ClumpDao;
import dao.SourceDao;
import entity.Agency;
import entity.Source;


	public class SourceController {
		
		private static SourceController instance;
		
		
		  private SourceController(ShowSourcesInClumpBean ssicb) {
			  
		  }
		
		  private SourceController(ShowObjectsInAreaBean sciab){
			  
		  }
		  
		  private SourceController(YoungSourceObjectBean ysob){
			  
		  }
		  
		  
		  
		  private SourceController() {
			  
		  }
		  
		  public static synchronized SourceController getInstance(YoungSourceObjectBean ysob){
			  if(instance == null)
			      instance = new SourceController(ysob);
			    return instance;
		  }
		  
		  
		  public static synchronized SourceController getInstance(ShowSourcesInClumpBean ssicb){
			  if(instance == null)
			      instance = new SourceController(ssicb);
			    return instance;
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
		  
		  public Vector<String[]> getSourcesInClump(int clumpId, Double bandRes) {
			  	
			  	Vector<String[]> sources = new Vector<String[]>();
			    SourceDao sd = new SourceDao();
			    sources = sd.findSourcesInClump(clumpId, bandRes);
			    
			    return sources;
			    
		  }
		  
		  public Vector<String[]> getYoungSourceObject(int clumpId){
			  
			  Vector<String[]> yso = new Vector<String[]>();
			  SourceDao sd = new SourceDao();
			  yso = sd.findYoungSourceObject(clumpId);
			  return yso;
		  }
}
