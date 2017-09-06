package entity;

import java.util.Vector;

public class Tool {
	
	//TODO CHANGE BANDE IN A VECTOR! 
	private String toolName;
	private int mapId;
	private Vector<Double> bands;
	
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
	
	public void addBand(Double resolution) {
		bands.add(resolution);
		}
	public void setBandList(Vector<Double> bands) {
			this.bands = bands;
	}
	public Vector<Double> getBandList() {
		return this.bands;
	}
}
