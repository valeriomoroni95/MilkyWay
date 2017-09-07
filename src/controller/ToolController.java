package controller;

import java.sql.SQLException;
import java.util.Vector;

import boundary.ToolBean;
import dao.BandDao;
import dao.MapDao;
import dao.ToolDao;
import entity.Map;
import entity.Tool;

public class ToolController {
  
	private static ToolController instance;
  
	private ToolController(ToolBean tb) {
    
	}
  
	private ToolController() {
	  
	}
  
	public static synchronized ToolController getInstance(ToolBean tb) {
		if(instance == null)
			instance = new ToolController(tb);
    return instance;
	}
  
	public Vector<Tool> getToolsList() throws SQLException{
		
		Vector<Tool> tools = new Vector<Tool>();
        
		ToolDao toolDao = new ToolDao();
    
		tools = toolDao.showTools();
    
		return tools;
	
   }

		public Vector<Double> getBandResolutions() throws SQLException{
		
		Vector<Double> resolutions = new Vector<Double>();
        
		BandDao bandDao = new BandDao();
    
		resolutions = bandDao.showBandResolutions();
    
		return resolutions;
	
   }
	
	public Vector<Map> getMapsList() throws SQLException{
		
		Vector<Map> maps = new Vector<Map>();
        		
		MapDao md = new MapDao();
		
		maps = md.showMaps();
    
		return maps;
	
   }
  
  public boolean InsertTool(String name, int mapId, Vector<Double> bands) {
		boolean bool = false;
		try {
			bool=ToolDao.isToolPresent(name, mapId, bands);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return bool;
		
	}
  
  
  
}