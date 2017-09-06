package entity;

public class Tool {
	
	
	private String toolName;
	private int mapId;
	private Double[] band;
	
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
	
	public void setBand(Double resolution, Double lenght) {
		this.band[0] = resolution;
		this.band[1] = lenght;
	}
	
	public Double getResolution() {
		return band[0];
	}
	
	public Double getLenght() {
		return band[1];
		}
}
