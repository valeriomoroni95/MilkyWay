package boundary;

import java.sql.SQLException;
import java.util.Vector;

import controller.ToolController;
import entity.Map;
import entity.Tool;

public class ToolBean {
	
	private Vector<Tool> tools = new Vector<Tool>();
	private Vector<Map> maps = new Vector<Map>();
	private String name;
	private int mapId;
	private Vector<Double> bands = new Vector<Double>();
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public void setBands(Vector<Double> bands) {
		this.bands = bands;
	}
	
	public Vector<Tool> getTools() {
		return tools;
	}

	public void setTools(Vector<Tool> tools) {
		this.tools = tools;
	}

	public Vector<Map> getMaps() {
		return maps;
	}

	public void setMaps(Vector<Map> maps) {
		this.maps = maps;
	}

	public String getName() {
		return this.name;
	}
	
	public int getMapId() {
		return this.mapId;
	}
	
	public Vector<Double> getBands(){
		return this.bands;
	}
	
	
	public void importTools() throws SQLException { 
		ToolController controller = ToolController.getInstance(this);
		this.tools = controller.getToolsList();
		}
	
	public void importBands() throws SQLException { 
		ToolController controller = ToolController.getInstance(this);
		this.bands = controller.getBandResolutions();
	}
	
	public void importMaps() throws SQLException { 
		ToolController controller = ToolController.getInstance(this);
		this.maps = controller.getMapsList();
		}
	
	public boolean validate(){
		
		ToolController controller = ToolController.getInstance(this);
		if( !controller.InsertTool(this.name, this.mapId, this.bands)){
			return false;
		}
		return true;
		
	}
	
}	
