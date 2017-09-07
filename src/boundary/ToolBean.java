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
	
	public void setMapId(int mapName) {
		this.mapId = mapId;
	}
	public void setBands(Vector<Double> bands) {
		this.bands = bands;
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
	
	/*public List<String> getTools(){
		return this.tools;
	}*/
	
	public void getTools() throws SQLException {
		ToolController controller = ToolController.getInstance(this);
		this.tools = controller.getToolsList();
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
