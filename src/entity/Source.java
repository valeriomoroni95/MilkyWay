package entity;

public class Source {

	private int source_id;
	private double brightness;
	private double longitude;
	private double latitude;
	private int source_x;
	private String mapcode;
	
	public Source(int source_id, double brightness, double longitude, double latitude,int source_x, String mapcode){
		this.source_id = source_id;
		this.brightness = brightness;
		this.longitude = longitude;
		this.latitude = latitude;
		this.source_x = source_x;
		this.mapcode = mapcode;
	}
	
	public String getMapcode() {
		return mapcode;
	}

	public void setMapcode(String mapcode) {
		this.mapcode = mapcode;
	}

	public Source(int source_id, double brightness, double longitude, double latitude){
		this.source_id = source_id;
		this.brightness = brightness;
		this.longitude = longitude;
		this.latitude = latitude;
		
	}

	public int getSource_id() {
		return source_id;
	}

	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}

	public double getBrightness() {
		return brightness;
	}

	public void setBrightness(double brightness) {
		this.brightness = brightness;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getSource_x() {
		return source_x;
	}

	public void setSource_x(int source_x) {
		this.source_x = source_x;
	}
	
	
	
}
