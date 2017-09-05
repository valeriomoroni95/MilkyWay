package entity;

public class Map {
	private int mapId;
	private String mapName;
	
	public Map(int mapId, String mapName) {
		this.mapId = mapId;
		this.mapName = mapName;
	}
	
	public int getMapId() {
		return mapId;
	}
	
	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
}
