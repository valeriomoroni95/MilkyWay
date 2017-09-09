package entity;

import java.util.Vector;
import entity.Agency;
//import java.sql.Date;

public class Satellite {
	private String satelliteName;
	//private Date satelliteStart;
	//private Date satelliteEnd;
	private String satelliteStart;
	private String satelliteEnd;
	private Vector<String> tools;
	private String duration;
	private Vector<Agency> agencies;
	
	
	public Satellite(String satelliteName, String satelliteStart, String satelliteEnd, Vector<String> tools, Vector<Agency> agencies) {
		this.satelliteName = satelliteName;
		this.satelliteStart = satelliteStart;
		this.satelliteEnd = satelliteEnd;
		this.tools = tools;
		this.agencies = agencies;
		if(!satelliteEnd.equals("null"))
			this.duration = calculateDuration();
	}
	
	
	@Override
	public String toString() {
		return "Satellite [satelliteName=" + satelliteName + ", satelliteStart=" + satelliteStart + ", satelliteEnd="
				+ satelliteEnd + ", tools=" + tools + ", duration=" + duration + ", agencies=" + agencies + "]";
	}


	public Vector<Agency> getAgencies() {
		return agencies;
	}


	public void setAgencies(Vector<Agency> agencies) {
		this.agencies = agencies;
	}


	public Vector<String> getTools() {
		return this.tools;
	}
	public void setTools(Vector<String> tools) {
		this.tools = tools;
	}
	
	public String getSatelliteName() {
		return satelliteName;
	}
	
	public void setSatelliteName(String satelliteName) {
		this.satelliteName = satelliteName;
	}
	
	public String getSatelliteStart() {
		return satelliteStart;
	}
	
	public void setSatelliteStart(String satelliteStart) {
		this.satelliteStart = satelliteStart;
	}
	
	public String getSatelliteEnd() {
		return satelliteEnd;
	}
	
	public void setSatelliteEnd(String satelliteEnd) {
		this.satelliteEnd = satelliteEnd;
	}
	
	public String calculateDuration() {
		String[] x = this.satelliteStart.split("-");
		String[] y = this.satelliteEnd.split("-");
		int startYear = Integer.parseInt(x[0]);
		int endYear = Integer.parseInt(y[0]);
		int startMonth = Integer.parseInt(x[1]);
		int endMonth = Integer.parseInt(y[1]);
		int startDay = Integer.parseInt(x[2]);
		int endDay = Integer.parseInt(y[2]);
		int months;
		int years;
		int days;
		
		days = (-startYear+endYear)*365 + (-startMonth+endMonth)*30 - startDay + endDay;
		years = days/365;
		months = (days-years*365)/30;
		days = days - years*365 - months*30;
		String k = Integer.toString(years) + 
				" years, " + Integer.toString(months) + " months and " + Integer.toString(days) + " days.";
		//System.out.println(k);
		return k;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
