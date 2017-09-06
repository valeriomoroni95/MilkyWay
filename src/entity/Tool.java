package entity;

public class Tool {

	private String toolName;
	private int mapId;
	
	public Tool( String toolName, int mapId) {
		
		this.toolName = toolName;
		this.mapId = mapId;
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
