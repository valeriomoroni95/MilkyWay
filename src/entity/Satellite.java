package entity;

import java.sql.Date;

public class Satellite {
	private int satelliteId;
	private String satelliteName;
	private Date satelliteStart;
	private Date satelliteEnd;
	
	public Satellite() {
		
	}
	
	public int getSatelliteId() {
		return satelliteId;
	}
	
	public String getSatelliteName() {
		return satelliteName;
	}
	
	public void setSatelliteName(String satelliteName) {
		this.satelliteName = satelliteName;
	}
	
	public Date getSatelliteStart() {
		return satelliteStart;
	}
	
	public void setSatelliteStart(Date satelliteStart) {
		this.satelliteStart = satelliteStart;
	}
	
	public Date getSatelliteEnd() {
		return satelliteEnd;
	}
	
	public void setSatelliteEnd(Date satelliteEnd) {
		this.satelliteEnd = satelliteEnd;
	}
	
}
