package boundary;

import java.sql.SQLException;
import java.util.Vector;

import controller.ClumpController;
import entity.Clump;

public class ShowMassiveStarBean {
	
	private Vector<Clump> stars;
	
	
	
	public void importMassiveStarsList() throws SQLException {
		ClumpController controller = ClumpController.getInstance(this);
		this.stars = controller.getMassiveStars();

	}

	public Vector<Clump> getStars() {
		return stars;
	}

	public void setStars(Vector<Clump> stars) {
		this.stars = stars;
	}
}