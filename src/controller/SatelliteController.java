package controller;

import java.sql.SQLException;
import java.util.Vector;

import boundary.SatelliteBean;
import dao.SatelliteDao;
import entity.Satellite;

public class SatelliteController {
  private static SatelliteController instance;
  
  private SatelliteController(SatelliteBean sb) {
    
  }
  
  private SatelliteController() {
	  
  }
  public static synchronized SatelliteController getInstance(SatelliteBean sb) {
    if(instance == null)
      instance = new SatelliteController(sb);
    return instance;
  }
  
  public Vector<Satellite> getSatellitesList() throws SQLException{
    Vector<Satellite> satellite = new Vector<Satellite>();
        
    SatelliteDao satelliteDao = new SatelliteDao();
    
    satellite = satelliteDao.showSatellites();
    
	return satellite;
	
  }
  
  public boolean InsertSatellites(String name, String start, String end, Vector<String> tools, Vector<String> agencies) {
		boolean bool = false;
		try {
			bool=SatelliteDao.isSatellitePresent(name, start, end, tools, agencies);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return bool;
		
	}
  
  
  
}