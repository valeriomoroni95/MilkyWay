package boundary;

import java.sql.SQLException;
import java.util.Vector;

import controller.SourceController;

public class ShowSourcesInClumpBean {
	
	private int clumpId;
	private double bandRes;
	private Vector<String[]> sourcesInClump;
	
	public void importSourcesInClump() throws SQLException {
		SourceController controller = SourceController.getInstance(this);
		controller.getSourcesInClump(this.getClumpId(), this.getBandRes());
		this.setBandRes(bandRes);
		this.setClumpId(clumpId);
	}

	public Vector<String[]> getSourcesInClump(){
		return sourcesInClump;
	}
	
	public int getClumpId() {
		return clumpId;
	}

	public void setClumpId(int clumpId) {
		this.clumpId = clumpId;
	}

	public double getBandRes() {
		return bandRes;
	}

	public void setBandRes(double bandRes) {
		this.bandRes = bandRes;
	}
}
