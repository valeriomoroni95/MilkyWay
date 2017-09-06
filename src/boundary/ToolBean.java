package boundary;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import controller.ToolController;
import entity.Tool;

public class ToolBean {
	
	private Vector<Tool> tools = new Vector<Tool>();
	private String name;
	private String mapName;
	private Vector<Double> bands = new Vector<Double>();
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public void setBands(Vector<Double> bands) {
		this.bands = bands;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getMapName() {
		return this.mapName;
	}
	
	public Vector<Double> getBands(){
		return this.bands;
	}
	
	/*public List<String> getTools(){
		return this.tools;
	}*/
	
	public void getTools() throws SQLException {
		ToolController controller = ToolController.getInstance(this);
		this.tools = controller.getToolsList(); //is a Vector<Tool> type;
	}
	
	public boolean validate(){
		
		ToolController controller = ToolController.getInstance(this);
		if( !controller.InsertTool(this.name, this.mapName, this.bands)){
			return false;
		}
		return true;
		
	}
	
}	
