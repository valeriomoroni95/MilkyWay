package controller;

import java.sql.SQLException;
import java.util.Vector;

import boundary.ClumpMassesBean;
import dao.MassDao;
import entity.ClumpMass;

public class ClumpMassesController {

	private static ClumpMassesController instance;

	private ClumpMassesController(ClumpMassesBean cmb) {
    
	}
  
	private ClumpMassesController() {
	  
	}
  
	public static synchronized ClumpMassesController getInstance(ClumpMassesBean cmb) {
		if(instance == null)
			instance = new ClumpMassesController(cmb);
    return instance;
	}
	
	public Vector<ClumpMass> getMasses() throws SQLException, ClassNotFoundException{ 
		
		Vector<ClumpMass> masses = new Vector<ClumpMass>();
    
		MassDao massDao = new MassDao();
   		masses = massDao.loadMasses();
   		return masses;
	
    }
	
	public String[] getStats() throws ClassNotFoundException{
		
		String[] list = new String[4];
		MassDao md = new MassDao();
		list = md.statsMass();
		return list;
		
	}
	

}
