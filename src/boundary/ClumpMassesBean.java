package boundary;

import java.sql.SQLException;
import java.util.Vector;

import controller.ClumpMassesController;
import entity.ClumpMass;



public class ClumpMassesBean {
		
		private Vector<ClumpMass> masses;
		
		
		
		public void importClumpMasses() throws SQLException, ClassNotFoundException {
			ClumpMassesController controller = ClumpMassesController.getInstance(this);
			this.setMasses(controller.getMasses());

		}



		public Vector<ClumpMass> getMasses() {
			return masses;
		}



		public void setMasses(Vector<ClumpMass> masses) {
			this.masses = masses;
		}

}
