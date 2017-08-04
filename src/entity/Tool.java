package entity;

public class Tool {

	private int toolId;
	private int satelliteId;
	private String toolName;
	private int mapId;
	
	public Tool(int toolId, int satelliteId, String toolName, int mapId) {
		
		this.toolId = toolId;
		this.satelliteId = satelliteId;
		this.toolName = toolName;
		this.mapId = mapId;
	}
	
	public int getToolId() {
		return toolId;
	}
	
	public int getToolSatelliteId() {
		return satelliteId;
	}
	
	public String getToolName() {
		return toolName;
	}
	
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	
	public int getMapId() {
		return mapId;
	}
	
}
