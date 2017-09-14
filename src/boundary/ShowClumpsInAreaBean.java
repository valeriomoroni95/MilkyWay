package boundary;

import java.sql.SQLException;
import java.util.Vector;
import controller.ClumpController;
import controller.SourceController;

public class ShowClumpsInAreaBean {
	
	private Double cLatitude;
	private Double cLongitude; 
	private Double sLatitude;
	private Double sLongitude;
	private Double lenght;
	private boolean isCircle;
	private Vector<String[]> clumpsInArea;
	private Vector<String[]> sourcesInArea;
	
	public void importClumpsInArea() throws SQLException {
		ClumpController controller = ClumpController.getInstance(this);
		this.setClumpsInArea(controller.getClumpsInArea(this.getcLatitude(), this.getcLongitude(), this.getLenght(), this.isCircle()));
	}
	
	public void importSourcesInArea() throws SQLException {
		SourceController controller = SourceController.getInstance(this);
		this.setSourcesInArea(controller.getSourcesInArea(this.getsLatitude(), this.getsLongitude(), this.getLenght(), this.isCircle()));
	}

	public Vector<String[]> getClumpsInArea() {
		return clumpsInArea;
	}

	public void setClumpsInArea(Vector<String[]> clumpsInArea) {
		this.clumpsInArea = clumpsInArea;
	}

	public Double getsLatitude() {
		return sLatitude;
	}

	public void setsLatitude(Double sLatitude) {
		this.sLatitude = sLatitude;
	}

	public Double getsLongitude() {
		return sLongitude;
	}

	public void setsLongitude(Double sLongitude) {
		this.sLongitude = sLongitude;
	}

	public Vector<String[]> getSourcesInArea() {
		return sourcesInArea;
	}

	public void setSourcesInArea(Vector<String[]> sourcesInArea) {
		this.sourcesInArea = sourcesInArea;
	}

	public Double getcLatitude() {
		return cLatitude;
	}

	public void setLatitude(Double latitude) {
		this.cLatitude = latitude;
	}

	public Double getcLongitude() {
		return cLongitude;
	}

	public void setcLongitude(Double longitude) {
		this.cLongitude = longitude;
	}

	public Double getLenght() {
		return lenght;
	}

	public void setLenght(Double lenght) {
		this.lenght = lenght;
	}

	public boolean isCircle() {
		return isCircle;
	}

	public void setCircle(boolean isCircle) {
		this.isCircle = isCircle;
	}

}
