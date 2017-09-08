package entity;

public class Agency {

	private int agencyId;
	private String agencyName;
	
	public Agency() {
		
	}
	
	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public Agency(int agencyId, String agencyName) {
		this.agencyId = agencyId;
		this.agencyName = agencyName;
	}
	
	public int getAgencyId() {
		return agencyId;
	}
	
	public String getAgencyName() {
		return agencyName;
	}
	
}
