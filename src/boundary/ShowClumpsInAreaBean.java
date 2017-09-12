package boundary;

import java.sql.SQLException;
import java.util.Vector;
import controller.ClumpController;

public class ShowClumpsInAreaBean {
	
	private Double latitude;
	private Double longitude; 
	private Double lenght;
	private boolean isCircle;
	private Vector<String[]> clumpsInArea;
	
	public void importClumpsInArea() throws SQLException {
		ClumpController controller = ClumpController.getInstance(this);
		this.setClumpsInArea(controller.getClumpsInArea(this.getLatitude(), this.getLongitude(), this.getLenght(), this.isCircle()));

	}

	public Vector<String[]> getClumpsInArea() {
		return clumpsInArea;
	}

	public void setClumpsInArea(Vector<String[]> clumpsInArea) {
		this.clumpsInArea = clumpsInArea;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
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
