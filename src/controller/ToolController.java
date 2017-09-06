package controller;

import java.sql.SQLException;
import java.util.Vector;

import boundary.ToolBean;
import dao.ToolDao;
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
    
		tools = ToolDao.showTools();
    
		return tools;
	
  }
  
  public boolean InsertTool(String name, String mapName, Vector<Double> bands) {
		boolean bool = false;
		try {
			bool=ToolDao.isToolPresent(name, mapName, bands);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return bool;
		
	}
  
  
  
}