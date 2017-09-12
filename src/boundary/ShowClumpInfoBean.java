package boundary;

import java.sql.SQLException;
import java.util.Vector;
import controller.ClumpController;

public class ShowClumpInfoBean {
	
	private int clumpId;
	private Vector<String[]> clumpInfo;
	
	public void importClumpsInfo() throws SQLException {
		ClumpController controller = ClumpController.getInstance(this);
		this.setClumpInfo(controller.getClumpInfo(this.getClumpId()));

	}

	public int getClumpId() {
		return this.clumpId;
	}

	public void setClumpId(int clumpId) {
		this.clumpId = clumpId;
	}

	public Vector<String[]> getClumpInfo() {
		return clumpInfo;
	}

	public void setClumpInfo(Vector<String[]> clumpInfo) {
		this.clumpInfo = clumpInfo;
	}

}
