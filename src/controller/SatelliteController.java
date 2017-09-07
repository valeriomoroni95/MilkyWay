package controller;

import java.sql.SQLException;
import java.util.Vector;

import boundary.SatelliteBean;
import dao.AgencyDao;
import dao.SatelliteDao;
import dao.ToolDao;
import entity.Agency;
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
	  
    Vector<Satellite> satellites = new Vector<Satellite>();
    
    System.out.println("SatelliteController.java: getSatellitesList Vector" + satellites);
        
    SatelliteDao satelliteDao = new SatelliteDao();
    
    System.out.println("SatelliteController.java: getSatellitesList satelliteDao" + satelliteDao);
    
    satellites = satelliteDao.showSatellites();
    
    System.out.println("SatelliteController.java: getSatelliteList() satellite " + satellites);
    
	return satellites;
	
  }
  
  public Vector<String> getTools() throws SQLException{
	    Vector<String> tools = new Vector<String>();
	    ToolDao toolDao = new ToolDao();
	    tools = toolDao.showToolNames();
	    return tools;
  }
  
  public Vector<Agency> getAgencies() throws SQLException{
	    Vector<Agency> agencies = new Vector<Agency>();
	    AgencyDao agencyDao = new AgencyDao();
	    agencies = agencyDao.showAgencies();
	    return agencies;
  	}
  
  public boolean InsertSatellites(String name, String start, String end, Vector<String> tools, Vector<String> agencies) {
		boolean bool = false;
		System.out.println("SatelliteController.java: InsertSatelltes " + name + start + end + tools + agencies);
		try {
			bool=SatelliteDao.isSatellitePresent(name, start, end, tools, agencies);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return bool;
		
	}
  
  
  
}