package entity;

public class ClumpMass {
	
	private int clumpId;
	private double totalMass;
	
	public ClumpMass(int clumpId, double totalMass){
		this.clumpId =clumpId;
		this.totalMass = totalMass;
		
	}

	public int getClumpId() {
		return clumpId;
	}

	public void setClumpId(int clumpId) {
		this.clumpId = clumpId;
	}

	public double getTotalMass() {
		return totalMass;
	}

	public void setTotalMass(double totalMass) {
		this.totalMass = totalMass;
	}

}
