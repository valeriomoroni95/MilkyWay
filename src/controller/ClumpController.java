package controller;

import boundary.ClumpBean;


public class ClumpController {
	
	private static ClumpController instance;
	  
	  private ClumpController(ClumpBean cb) {
	    
	  }
	  
	  private ClumpController() {
		  
	  }
	  public static synchronized ClumpController getInstance(ClumpBean cb) {
	    if(instance == null)
	      instance = new ClumpController(cb);
	    return instance;
	  }
	  
	  

}
