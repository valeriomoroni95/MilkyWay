package boundary;

import java.sql.SQLException;
import java.util.Vector;

import controller.ClumpMassesController;
import entity.ClumpMass;



public class ClumpMassesBean {
		
		private Vector<ClumpMass> masses;
		private String[] stats;
		
		
		
		public void importClumpMasses() throws SQLException, ClassNotFoundException {
			ClumpMassesController controller = ClumpMassesController.getInstance(this);
			masses = controller.getMasses();

		}



		public Vector<ClumpMass> getMasses() {
			return masses;
		}



		public void setMasses(Vector<ClumpMass> masses) {
			this.masses = masses;
		}
		
		public void importStats() throws SQLException, ClassNotFoundException {
			ClumpMassesController controller = ClumpMassesController.getInstance(this);
			this.stats =controller.getStats();
		}



		public String[] getStats() {
			return stats;
		}



		public void setStats(String[] stats) {
			this.stats = stats;
		}

}
