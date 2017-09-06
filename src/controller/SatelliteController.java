package controller;

import java.sql.SQLException;
import java.util.Vector;

import dao.SatelliteDao;
import entity.Satellite;

public class SatelliteController {
  private static SatelliteController instance;
  
  private SatelliteController() {
    
  }
  public static SatelliteController getInstance() {
    if(instance == null)
      instance = new SatelliteController();
    return instance;
  }
  
  public Vector<Satellite> getSatellitesList() throws SQLException{
    Vector<Satellite> satellite = new Vector<Satellite>();
        
    SatelliteDao satelliteDao = new SatelliteDao();
    
    satellite = satelliteDao.showSatellites();
    
	return satellite;
	
  }
}