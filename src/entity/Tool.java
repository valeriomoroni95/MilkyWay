package entity;

import java.util.Vector;

public class Tool {
	
	//TODO CHANGE BANDE IN A VECTOR! 
	private String toolName;
	private int mapId;
	private String mapName;
	private Vector<Double> bands;
	
	public Tool( String toolName, int mapId, String mapName) {
		
		this.toolName = toolName;
		this.mapId = mapId;
		this.mapName = mapName;
	}

	public String getToolName() {
		return toolName;
	}
	
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	
	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public Vector<Double> getBands() {
		return bands;
	}

	public void setBands(Vector<Double> bands) {
		this.bands = bands;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
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
