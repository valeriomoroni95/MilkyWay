package entity;

//import java.sql.Date;

public class Satellite {
	private int satelliteId;
	private String satelliteName;
	//private Date satelliteStart;
	//private Date satelliteEnd;
	private String satelliteStart;
	private String satelliteEnd;
	public Satellite(int satelliteId, String satelliteName, String satelliteStart, String satelliteEnd) {
		this.satelliteId = satelliteId;
		this.satelliteName = satelliteName;
		this.satelliteStart = satelliteStart;
		this.satelliteEnd = satelliteEnd;
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
	
}
/* CONVERSIONE DATA FORMATO gg/mm/aaaa per calcolare DurataOperazione:
 * String[] x = satelliteStart.split("/");
 * String[] y = satelliteEnd.split("/");
 * int startYear = parseInt(x[2]);
 * int endYear = parseInt(y[2]);
 * int startMonth = parseInt(x[1]);
 * int endMonth = parseInt(y[1]);
 * int months;
 * int years;
 * if (endMonth > startMonth){
 * 		months = endMonth - startMonth;
 * 		years = endYear - startYear;
 * }
 * else{
 * 		months = 12 - endMonth - startMonth;
 * 		years = endYear - startYear - 1;
 * }
 * System.out.println("Il satellite è attivo da " + years + " anni e " + months + " mesi.");
 * */
